/*
 * Copyright (C) 2018 - wanli <wanlinus@qq.com>
 *
 * This file is part of emooc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.wanlinus.emooc.service.impl;

import cn.wanlinus.emooc.annotation.RegisterAnnotation;
import cn.wanlinus.emooc.annotation.UserAnnotation;
import cn.wanlinus.emooc.commons.ResultData;
import cn.wanlinus.emooc.domain.Captcha;
import cn.wanlinus.emooc.domain.Collection;
import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.dto.GenderPieDTO;
import cn.wanlinus.emooc.dto.UserDetailsDTO;
import cn.wanlinus.emooc.dto.UserRegisterDTO;
import cn.wanlinus.emooc.enums.EmoocLogType;
import cn.wanlinus.emooc.enums.Gender;
import cn.wanlinus.emooc.enums.UserStatus;
import cn.wanlinus.emooc.persistence.CaptchaRepository;
import cn.wanlinus.emooc.persistence.UserRepository;
import cn.wanlinus.emooc.service.CollectionService;
import cn.wanlinus.emooc.service.CommonService;
import cn.wanlinus.emooc.service.EmoocLogService;
import cn.wanlinus.emooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static cn.wanlinus.emooc.utils.AuthUtils.getUsername;
import static cn.wanlinus.emooc.utils.CommonUtils.*;

/**
 * @author wanli
 * @date 2018-03-06 16:25
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmoocLogService logService;
    @Autowired
    private CaptchaRepository captchaRepository;
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private CommonService commonService;

    @Value("${emooc.host}")
    private String host;
    @Value("${server.port}")
    private String port;


    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<GenderPieDTO> genderPie() {
        Map<String, Integer> mm = userRepository.genderPie();
        List<GenderPieDTO> pies = new ArrayList<>();
        pies.add(new GenderPieDTO("男", mm.get("0")));
        pies.add(new GenderPieDTO("女", mm.get("1")));
        pies.add(new GenderPieDTO("保密", mm.get("2")));
        return pies;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public ResultData<String> checkName(String username) {
        ResultData<String> resultData = new ResultData<>();
        User user = userRepository.findByUsername(username);
        if (user != null) {
            resultData.setCode(false);
            resultData.setMessage("用户名被占用");
        } else {
            resultData.setCode(true);
            resultData.setMessage("可以注册");
        }
        return resultData;


    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public ResultData<String> checkEmail(String email) {
        ResultData<String> resultData = new ResultData<>();
        User user = userRepository.findByEmail(email);
        if (user != null) {
            resultData.setCode(false);
            resultData.setMessage("邮箱被占用");
        } else {
            resultData.setCode(true);
            resultData.setMessage("可以注册");
        }
        return resultData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public ResultData<String> checkAll(String username, String email) {
        ResultData<String> resultData = new ResultData<>();
        Boolean code = true;
        String msg = "";
        if (userRepository.findByUsername(username) != null) {
            code = false;
            msg = " 用户名被占用";
        }
        if (userRepository.findByEmail(email) != null) {
            code = false;
            msg += " 邮箱被占用";
        }
        msg += " 请重新输入";
        resultData.setCode(code);
        resultData.setMessage(msg);
        return resultData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @RegisterAnnotation(type = EmoocLogType.USER_REGISTER)
    public ResultData<String> register(UserRegisterDTO dto) {
        ResultData<String> resultData = new ResultData<>();
        try {
            User user = new User(uid(), dto.getUsername(), md5Encrypt(dto.getPassword()), dto.getEmail());
            user.setUserStatus(UserStatus.INACTIVE);
            user.setRegisterTime(new Date());
            user.setGender(Gender.UNDEFINED);
            user.setAvatar("/data/teacher/img/emooc-29f0ca1c-c139-46e6-987d-2f489b3549af.jpg");
            User u = userRepository.save(user);
            if (u != null) {
                Captcha captcha = new Captcha();
                captcha.setId(capid());
                captcha.setUser(u);
                captcha.setTime(new Date());
                //30分钟
                captcha.setEffectiveTime(30 * 60 * 1000);
                captcha.setStatus(true);
                Captcha code = captchaRepository.save(captcha);
                String msg = "点击https://" + host + ":" + port + "/active/user/" + code.getUser().getId() + "/" + code.getId() + " 激活用户, 30分钟有效";
                commonService.simpleSendMail("用户注册", dto.getEmail(), msg);
                resultData.setCode(true);
                resultData.setMessage("注册成功");
            } else {
                resultData.setCode(false);
                resultData.setMessage("注册出现错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setCode(false);
            resultData.setMessage("注册出现错误");
        }
        return resultData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<User> pageUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetailsDTO userDetails(String id) {
        User user = userRepository.findOne(id);
        return user != null ? new UserDetailsDTO(user) : new UserDetailsDTO();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long countUsers() {
        return userRepository.count();
    }

    @Override
    public User getCurrentUser() {
        return userRepository.findByUsername(getUsername());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Long countUserLogin(Date date) {
        return logService.countUserLogin(date);
    }

    @Override
    public List<Long> userLoginStatistics(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(countUserLogin(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public Long countUserRegister(Date date) {
        return logService.countUserRegister(date);
    }

    @Override
    public List<Long> userRegisterStatistics(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(countUserRegister(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @UserAnnotation(type = EmoocLogType.USER_ACTIVATED)
    public ResultData<String> active(String userId, String captchaId) {
        ResultData<String> resultData = new ResultData<>();
        String msg;
        Captcha captcha = captchaRepository.findOne(captchaId);
        if (captcha != null) {
            User user = captcha.getUser();
            if (user != null) {
                if ((System.currentTimeMillis() - captcha.getTime().getTime() <= captcha.getEffectiveTime()) && captcha.getStatus()) {
                    user.setUserStatus(UserStatus.ACTIVATED);
                    captcha.setStatus(false);
                    resultData.setCode(true);
                    msg = "激活成功";
                } else {
                    resultData.setCode(false);
                    msg = "激活码失效";
                }
            } else {
                resultData.setCode(false);
                msg = "用户不存在";
            }
        } else {
            resultData.setCode(false);
            msg = "激活码错误";
        }
        resultData.setMessage(msg);
        return resultData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @UserAnnotation(type = EmoocLogType.USER_ADD_COLLECTION)
    public ResultData<String> collectionCourse(String courseId) {
        ResultData<String> resultData = new ResultData<>();
        User user = userRepository.findByUsername(getUsername());
        if (collectionService.addCollection(user.getId(), courseId) != null) {
            resultData.setCode(true);
            resultData.setMessage("收藏成功");
        } else {
            resultData.setCode(false);
            resultData.setMessage("收藏失败");
        }
        return resultData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @UserAnnotation(type = EmoocLogType.USER_CANCEL_COLLECTION)
    public ResultData<String> collectionCourseCancel(String courseId) {
        ResultData<String> resultData = new ResultData<>();
        resultData.setCode(false);
        resultData.setMessage("操作失败,请稍后重试");
        User user = getCurrentUser();
        if (collectionService.removeCollection(user.getId(), courseId) != null) {
            resultData.setCode(true);
            resultData.setMessage("取消收藏");
        }
        return resultData;
    }

    @Override
    public ResultData<String> isCollectionCourse(String courseId) {
        ResultData<String> resultData = new ResultData<>();
        resultData.setCode(false);
        User user = getCurrentUser();
        List<Collection> collections = user.getCollections();
        for (Collection c : collections) {
            if (c.getCourse().getId().equals(courseId) && c.getStatus().equals(true)) {
                resultData.setCode(true);
                break;
            }
        }
        return resultData;
    }


}
