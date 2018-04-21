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
import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.dto.GenderPieDTO;
import cn.wanlinus.emooc.dto.UserDetailsDTO;
import cn.wanlinus.emooc.dto.UserRegisterDTO;
import cn.wanlinus.emooc.enums.EmoocLogType;
import cn.wanlinus.emooc.enums.EmoocRole;
import cn.wanlinus.emooc.enums.Gender;
import cn.wanlinus.emooc.enums.UserStatus;
import cn.wanlinus.emooc.persistence.EmoocLogRepository;
import cn.wanlinus.emooc.persistence.UserRepository;
import cn.wanlinus.emooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private EmoocLogRepository logRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<GenderPieDTO> genderPie() {
        Map<String, Integer> mm = userRepository.genderPie();
        List<GenderPieDTO> pies = new ArrayList<>();
        pies.add(new GenderPieDTO("男", mm.get("0")));
        pies.add(new GenderPieDTO("女", mm.get("1")));
        pies.add(new GenderPieDTO("未知", mm.get("2")));
        return pies;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Boolean checkName(String username) {
        return userRepository.findByUsername(username) != null;

    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Boolean checkEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    @RegisterAnnotation(type = EmoocLogType.USER_REGISTER)
    @Transactional(rollbackFor = Exception.class)
    public User register(UserRegisterDTO dto) {
        try {
            User user = new User(uid(), dto.getUsername(), md5Encrypt(dto.getPassword()), dto.getEmail());
            user.setUserStatus(UserStatus.INACTIVE);
            user.setRegisterTime(new Date());
            user.setGender(Gender.UNDEFINED);
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
    @Transactional(rollbackFor = Exception.class)
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Long countUserLogin(Date date) {
        return logRepository.countRoleType(EmoocRole.ROLE_USER, EmoocLogType.LOGIN, startDate(date), endDate(date));
    }


    @Override
    public Long countUserRegister(Date date) {
        return logRepository.countRoleType(EmoocRole.ROLE_USER, EmoocLogType.USER_REGISTER, startDate(date), endDate(date));
    }

}
