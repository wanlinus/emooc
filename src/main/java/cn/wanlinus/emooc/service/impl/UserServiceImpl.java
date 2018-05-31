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
import cn.wanlinus.emooc.domain.EmoocLog;
import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.dto.*;
import cn.wanlinus.emooc.enums.EmoocLogType;
import cn.wanlinus.emooc.enums.EmoocRole;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


    @Value("${web.upload-path}")
    private String uploadPath;

    @Value("${web.avatar-path}")
    private String avatarPath;


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
            user.setBirthday(new Date());
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

    @Override
    @UserAnnotation(type = EmoocLogType.USER_CHANGE_INFORMATION)
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> changeInformation(UserInformationDTO dto) {
        ResultData<String> resultData = new ResultData<>();
        try {
            User user = getCurrentUser();
            user.setRealname(dto.getRealname());
            user.setPosition(dto.getPosition());
            if (dto.getBirthday() != null) {
                user.setBirthday(dto.getBirthday());
            }
            user.setAddress(dto.getAddress());
            user.setGender(Gender.valueOf(dto.getGender()));
            user.setSignature(dto.getSignature());
            resultData.setCode(true);
            resultData.setMessage("修改成功");
        } catch (Exception e) {
            resultData.setCode(false);
            resultData.setMessage("系统出现错误啦, 请稍后再试");
            e.printStackTrace();
        }
        return resultData;
    }

    @Override
    @UserAnnotation(type = EmoocLogType.USER_CHANGE_AVATAR)
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> updateAvatar(MultipartFile userAvatar) {
        ResultData<String> resultData = new ResultData<>();
        try {
            String filename = saveFile(userAvatar, uploadPath, avatarPath);
            User user = getCurrentUser();
            user.setAvatar(filename);
            resultData.setCode(true);
            resultData.setMessage("更新成功");
            resultData.setData(filename);
        } catch (IOException e) {
            resultData.setCode(false);
            resultData.setMessage("更新失败");
            e.printStackTrace();
        }
        return resultData;
    }

    @Override
    public ResultData<BootstrapPaginationDataDTO<BootstrapPaginationDataLogDTO>> pageLog(Integer appointPage, Integer pageSize) {
        ResultData<BootstrapPaginationDataDTO<BootstrapPaginationDataLogDTO>> resultData = new ResultData<>();
        BootstrapPaginationDataDTO<BootstrapPaginationDataLogDTO> dto = new BootstrapPaginationDataDTO<>();
        try {
            String username = getCurrentUser().getUsername();
            Integer totalPage = (logService.countUserLogs(username).intValue() - 1) / pageSize + 1;
            appointPage = appointPage > totalPage ? totalPage : appointPage;
            dto.setTotalPage(totalPage);
            dto.setNextPage(appointPage < totalPage ? appointPage + 1 : totalPage);
            dto.setPrePage(appointPage > 1 ? appointPage - 1 : 1);
            dto.setCurrentPage(appointPage);
            List<EmoocLog> logs = logService.pageRoleLogger(EmoocRole.ROLE_USER, appointPage - 1, pageSize, username);
            List<BootstrapPaginationDataLogDTO> list = new ArrayList<>();
            for (EmoocLog log : logs) {
                BootstrapPaginationDataLogDTO logDTO = new BootstrapPaginationDataLogDTO();
                logDTO.setType(log.getType().getDescription());
                logDTO.setEquipment(log.getEquipment().substring(log.getEquipment().indexOf("(") + 1, log.getEquipment().indexOf(")")));
                logDTO.setIp(log.getIp());
                logDTO.setTime(dateFormatComplex(log.getTime()));
                list.add(logDTO);
            }
            dto.setData(list);
            resultData.setData(dto);
            resultData.setCode(true);
            resultData.setMessage("查询成功");
        } catch (Exception e) {
            resultData.setCode(false);
            resultData.setMessage("查询失败");
            e.printStackTrace();
        }
        return resultData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> forgetPassword(String email) {
        ResultData<String> resultData = new ResultData<>();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            resultData.setCode(false);
            resultData.setMessage("此邮箱没有注册");
        } else {
            Captcha captcha = new Captcha();
            captcha.setId(capid());
            captcha.setUser(user);
            captcha.setTime(new Date());
            //30分钟
            captcha.setEffectiveTime(30 * 60 * 1000);
            captcha.setStatus(true);
            captchaRepository.save(captcha);
            String code = forgetPasswordCapt();
            String msg = "您的激活码是: " + code + "\n30分钟有效[EMOOC]";
            commonService.simpleSendMail("重置密码", email, msg);
            resultData.setCode(true);
            resultData.setData(code);
            resultData.setMessage("请登录您的邮箱查看验证码");
        }
        return resultData;
    }

    @Override
    public ResultData<String> changePassword(String email, String password) {
        ResultData<String> resultData = new ResultData<>();
        try {
            User user = userRepository.findByEmail(email);
            user.setPassword(md5Encrypt(password));
            EmoocLog log = new EmoocLog();
            log.setId(userLogId());
            log.setWho(user.getUsername());
            log.setResult(true);
            log.setComment(email + password);
            log.setRole(EmoocRole.ROLE_USER);
            log.setType(EmoocLogType.USER_FORGET_AND_CHANGE_PASSWORD);
            log.setIp(Objects.requireNonNull(getRequest()).getRemoteAddr());
            log.setEquipment(getEquipment(getRequest()));
            log.setTime(new Date());
            logService.save(log);
            resultData.setCode(true);
            resultData.setMessage("修改成功");
        } catch (Exception e) {
            resultData.setCode(false);
            resultData.setMessage("系统错误,请稍后重试");
            e.printStackTrace();
        }
        return resultData;
    }

    @Override
    @UserAnnotation(type = EmoocLogType.USER_CHANGE_PASSWORD)
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> changePassword(UserChangePasswordDTO dto) {
        ResultData<String> resultData = new ResultData<>();
        try {
            User user = getCurrentUser();
            if (user.getPassword().equals(md5Encrypt(dto.getOldPassword()))) {
                user.setPassword(md5Encrypt(dto.getNewPassword()));
                resultData.setCode(true);
                resultData.setMessage("修改成功");
            } else {
                resultData.setCode(false);
                resultData.setMessage("修改失败,老密码您忘记了吧!");
            }
        } catch (Exception e) {
            resultData.setCode(false);
            resultData.setMessage("系统错误,修改失败");
            e.printStackTrace();
        }
        return resultData;
    }

    @Override
    @UserAnnotation(type = EmoocLogType.USER_CHANGE_EMAIL)
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> changeEmail(String newEmail) {
        ResultData<String> resultData = new ResultData<>();
        try {
            User user = getCurrentUser();
            user.setEmail(newEmail);
            resultData.setCode(true);
            resultData.setMessage("修改成功");
        } catch (Exception e) {
            resultData.setCode(false);
            resultData.setMessage("修改失败");
            e.printStackTrace();
        }
        return resultData;
    }

    @Override
    @UserAnnotation(type = EmoocLogType.USER_CHANGE_PHONE)
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> changePhone(String newPhone) {
        ResultData<String> resultData = new ResultData<>();
        try {
            User user = getCurrentUser();
            user.setTelephone(newPhone);
            resultData.setCode(true);
            resultData.setMessage("修改成功");
        } catch (Exception e) {
            resultData.setCode(false);
            resultData.setMessage("系统错误,修改失败");
            e.printStackTrace();
        }
        return resultData;
    }
}
