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

import cn.wanlinus.emooc.domain.EmoocLog;
import cn.wanlinus.emooc.dto.LayuiPaginationDataDTO;
import cn.wanlinus.emooc.dto.LoggerDTO;
import cn.wanlinus.emooc.enums.EmoocLogType;
import cn.wanlinus.emooc.enums.EmoocRole;
import cn.wanlinus.emooc.persistence.EmoocLogRepository;
import cn.wanlinus.emooc.service.EmoocLogService;
import cn.wanlinus.emooc.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wanli
 * @date 2018-04-16 19:47
 */
@Service
public class EmoocLogServiceImpl implements EmoocLogService {

    @Autowired
    private EmoocLogRepository logRepository;

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<EmoocLog> getTopUserLog(Integer startNum, Integer endNum) {
        return logRepository.getTopUserLog(startNum, endNum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<EmoocLog> getTopTeacherLog(Integer startNum, Integer endNum) {
        return logRepository.getTopTeacherLog(startNum, endNum);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public LayuiPaginationDataDTO<LoggerDTO> pageRoleLogger(EmoocRole role, int page, Integer limit) {
        LayuiPaginationDataDTO<LoggerDTO> dto = new LayuiPaginationDataDTO<>();
        try {
            dto.setCount(logRepository.countLog(role));
            List<EmoocLog> logs = logRepository.pageRoleLogger(role.ordinal(), limit * page, limit);
            List<LoggerDTO> list = new ArrayList<>();
            for (EmoocLog log : logs) {
                LoggerDTO d = new LoggerDTO();
                d.setId(log.getId());
                d.setWho(log.getWho());
                d.setTime(CommonUtils.dateFormatComplex(log.getTime()));
                d.setOperation(log.getType().getDescription());
                d.setResult(log.getResult());
                d.setIp(log.getIp());
                d.setEquipment(log.getEquipment());
                d.setComment(log.getComment());
                list.add(d);
            }
            dto.setData(list);
            dto.setCode(0);
            dto.setMsg("");
        } catch (Exception e) {
            dto.setCode(1);
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public Long countCourseLogs(Date time) {
        return logRepository.countCourses(time);
    }

    @Override
    public Long countVideoLogs(Date time) {
        return logRepository.countVideos(time);
    }

    @Override
    public Long countTeacherLogin(Date date) {
        return logRepository.countRoleType(EmoocRole.ROLE_TEACHER.ordinal(), EmoocLogType.LOGIN.ordinal(), date);
    }

    @Override
    public Long countTeacherRegister(Date date) {
        return logRepository.countLogType(EmoocLogType.TEACHER_REGISTER.ordinal(), date);
    }

    @Override
    public Long countUserLogin(Date date) {
        return logRepository.countRoleType(EmoocRole.ROLE_USER.ordinal(), EmoocLogType.LOGIN.ordinal(), date);
    }

    @Override
    public Long countUserRegister(Date date) {
        return logRepository.countLogType(EmoocLogType.USER_REGISTER.ordinal(), date);
    }

    @Override
    public Long countAdminLogin(Date date) {
        return logRepository.countRoleType(EmoocRole.ROLE_ADMIN.ordinal(), EmoocLogType.LOGIN.ordinal(), date);
    }
}
