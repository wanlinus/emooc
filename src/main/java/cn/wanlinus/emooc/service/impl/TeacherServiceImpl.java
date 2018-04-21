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

import cn.wanlinus.emooc.annotation.AdminAnnotation;
import cn.wanlinus.emooc.domain.Teacher;
import cn.wanlinus.emooc.dto.TeacherDetailsDTO;
import cn.wanlinus.emooc.enums.EmoocLogType;
import cn.wanlinus.emooc.enums.EmoocRole;
import cn.wanlinus.emooc.enums.Gender;
import cn.wanlinus.emooc.enums.TeacherStatus;
import cn.wanlinus.emooc.persistence.EmoocLogRepository;
import cn.wanlinus.emooc.persistence.TeacherRepository;
import cn.wanlinus.emooc.service.TeacherService;
import cn.wanlinus.emooc.utils.CommonUtils;
import org.apache.logging.log4j.spi.LoggerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static cn.wanlinus.emooc.utils.CommonUtils.endDate;
import static cn.wanlinus.emooc.utils.CommonUtils.startDate;

/**
 * @author wanli
 * @date 2018-03-07 10:00
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private EmoocLogRepository logRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Page<Teacher> pageTeacher(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }


    @Override
    @AdminAnnotation(type = EmoocLogType.TEACHER_REGISTER)
    @Transactional(rollbackFor = Exception.class)
    public Teacher addTeacher(TeacherDetailsDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setUsername(dto.getUsername());
        teacher.setEmail(dto.getEmail());
        teacher.setPassword(CommonUtils.md5Encrypt(dto.getPassword()));
        teacher.setId(CommonUtils.tid());
        teacher.setAvatar("");
        teacher.setGender(Gender.UNDEFINED);
        teacher.setStatus(TeacherStatus.A);
        teacher.setDetail("");
        teacher.setPosition("讲师");
        teacher.setSignature("这位老师很懒,暂时没有签名");
        return teacherRepository.save(teacher);
    }

    @Override
    public Long countTeachers() {
        return teacherRepository.count();
    }

    @Override
    public Long countTeachersLogin(Date date) {
        return logRepository.countRoleLogin(EmoocRole.ROLE_TEACHER, startDate(date), endDate(date));
    }
}
