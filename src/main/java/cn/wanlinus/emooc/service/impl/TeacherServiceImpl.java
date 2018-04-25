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
import cn.wanlinus.emooc.annotation.TeacherAnnotation;
import cn.wanlinus.emooc.domain.Course;
import cn.wanlinus.emooc.domain.Teacher;
import cn.wanlinus.emooc.dto.*;
import cn.wanlinus.emooc.enums.EmoocLogType;
import cn.wanlinus.emooc.enums.EmoocRole;
import cn.wanlinus.emooc.enums.Gender;
import cn.wanlinus.emooc.enums.TeacherStatus;
import cn.wanlinus.emooc.persistence.*;
import cn.wanlinus.emooc.service.CourseService;
import cn.wanlinus.emooc.service.TeacherService;
import cn.wanlinus.emooc.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cn.wanlinus.emooc.utils.AuthUtils.getUsername;
import static cn.wanlinus.emooc.utils.CommonUtils.*;

/**
 * @author wanli
 * @date 2018-03-07 10:00
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseCommentRepository commentRepository;
    @Autowired
    private UserStudyRepository userStudyRepository;


    @Autowired
    private EmoocLogRepository logRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public LayuiPaginationDataDTO<TeacherDetailsDTO> pageTeacher(LayuiPaginationDTO layuiPaginationDTO) {
        Pageable pageable = new PageRequest(layuiPaginationDTO.getPage() - 1, layuiPaginationDTO.getLimit());
        Page<Teacher> page = teacherRepository.findAll(pageable);
        List<TeacherDetailsDTO> list = new ArrayList<>();
        for (Teacher t : page.getContent()) {
            list.add(new TeacherDetailsDTO(t));
        }
        return new LayuiPaginationDataDTO<>(0, "", page.getTotalElements(), list);
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
        return logRepository.countRoleType(EmoocRole.ROLE_TEACHER, EmoocLogType.LOGIN, startDate(date), endDate(date));
    }

    @Override
    @TeacherAnnotation(type = EmoocLogType.TEACHER_ADD_COURSE)
    public Course addCourse(ThAddCourseDTO dto, String filename) {
        dto.setPath(filename);
        return courseService.saveCourse(teacherRepository.findByUsername(getUsername()), dto);
    }

    @Override
    public List<ThTopCoursesDTO> topCourses() {
        List<ThTopCoursesDTO> list = new ArrayList<>();
        List<Course> courses = courseRepository.findTopByTeacherId(teacherRepository.findByUsername(getUsername()).getId());
        for (Course c : courses) {
            ThTopCoursesDTO dto = new ThTopCoursesDTO();
            dto.setId(c.getId());
            dto.setName(c.getName());
            dto.setClassification(c.getClassification().getName());
            dto.setComments(commentRepository.commentsNum(c.getId()));
            dto.setDate(CommonUtils.dateFormatSimple(c.getCreateTime()));
            dto.setPicPath(c.getImagePath());
            dto.setStudy(userStudyRepository.studyNum(c.getId()));
            dto.setScore(c.getScore());
            list.add(dto);
        }
        return list;
    }
}
