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

import cn.wanlinus.emooc.dto.StatisticsDTO;
import cn.wanlinus.emooc.enums.EmoocLogType;
import cn.wanlinus.emooc.enums.EmoocRole;
import cn.wanlinus.emooc.persistence.AdminRepository;
import cn.wanlinus.emooc.persistence.EmoocLogRepository;
import cn.wanlinus.emooc.service.*;
import cn.wanlinus.emooc.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wanli
 * @date 2018-04-21 14:33
 */
@Service
public class AdminServiceImpl implements AdminService {

    /**
     * 获取30天的数据
     */
    public static final int MONTH = 30;
    @Autowired
    private EmoocLogService logService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmoocLogRepository logRepository;


    @Override
    public Long countAdminLogin(Date date) {
        return logRepository.countRoleType(EmoocRole.ROLE_ADMIN.ordinal(), EmoocLogType.LOGIN.ordinal(), date);
    }

    @Override
    public List<Long> adminLoginStatistics(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(countAdminLogin(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public StatisticsDTO statistics() {
        StatisticsDTO dto = new StatisticsDTO();
        List<String> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < MONTH; i++) {
            list.add(CommonUtils.dateFormatSimple(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        Collections.reverse(list);
        dto.setDate(list);
        dto.setAdmin(adminLoginStatistics(new Date(), MONTH));
        dto.setUserRegister(userService.userRegisterStatistics(new Date(), MONTH));
        dto.setUserLogin(userService.userLoginStatistics(new Date(), MONTH));
        dto.setTeacherRegister(teacherService.teacherRegisterStatistics(new Date(), MONTH));
        dto.setTeacherLogin(teacherService.teacherLoginStatistics(new Date(), MONTH));
        dto.setCourse(courseService.coursesAddStatistics(new Date(), MONTH));
        dto.setVideo(null);
        return dto;
    }

}
