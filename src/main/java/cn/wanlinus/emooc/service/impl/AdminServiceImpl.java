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

import cn.wanlinus.emooc.service.AdminService;
import cn.wanlinus.emooc.service.EmoocLogService;
import cn.wanlinus.emooc.service.TeacherService;
import cn.wanlinus.emooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wanli
 * @date 2018-04-21 14:33
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private EmoocLogService logService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;


    @Override
    public Map<String, Object> indexData() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("userLogs", logService.getTopUserLog(0, 12));
        map.put("teacherLogs", logService.getTopTeacherLog(0, 12));
        map.put("userNum", userService.countUsers());
        map.put("userRegister", userService.countUserRegister(new Date()));
        map.put("userLogin", userService.countUserLogin(new Date()));
        map.put("allTeachers", teacherService.countTeachers());
        map.put("teachersLogin", teacherService.countTeachersLogin(new Date()));
        //这两个数据先写死
        map.put("allCourses", 1000);
        map.put("addCourses", 50);
        return map;
    }
}
