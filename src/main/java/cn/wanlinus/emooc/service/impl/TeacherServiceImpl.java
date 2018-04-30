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
import cn.wanlinus.emooc.domain.CourseSection;
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

import java.util.*;

import static cn.wanlinus.emooc.utils.AuthUtils.getUsername;

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
    private CourseSectionRepository sectionRepository;
    @Autowired
    private UserStudyRepository userStudyRepository;
    @Autowired
    private EmoocLogRepository logRepository;

    /**
     * 获取当前教师
     *
     * @return Teacher
     */
    private Teacher getTeacher() {
        return teacherRepository.findByUsername(getUsername());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
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
    @Transactional(rollbackFor = Exception.class)
    @AdminAnnotation(type = EmoocLogType.TEACHER_REGISTER)
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
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Long countTeachers() {
        return teacherRepository.count();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Long countTeacherLogin(Date date) {
        return logRepository.countRoleType(EmoocRole.ROLE_TEACHER.ordinal(), EmoocLogType.LOGIN.ordinal(), date);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Long> teacherLoginStatistics(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(countTeacherLogin(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        Collections.reverse(list);
        return list;

    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Long countTeacherRegister(Date date) {
        return logRepository.countLogType(EmoocLogType.TEACHER_REGISTER.ordinal(), date);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @TeacherAnnotation(type = EmoocLogType.TEACHER_ADD_COURSE)
    public Course addCourse(ThAddCourseDTO dto, String filename) {
        dto.setPath(filename);
        return courseService.saveCourse(getTeacher(), dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<ThCourseDTO> topCourses() {
        List<ThCourseDTO> list = new ArrayList<>();
        List<Course> courses = courseRepository.findTopByTeacherId(getTeacher().getId());
        for (Course c : courses) {
            list.add(course2DTO(c));
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public ThCourseDTO getCourseDetails(String courseId) {
        return course2DTO(courseService.getCourse(courseId));

    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Long> teacherRegisterStatistics(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(countTeacherRegister(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourseSection addSection(SectionAddDTO dto) {
        return courseService.addSection(dto);
    }

    @Override
    public List<CourseSection> getSections(String courseId) {
        return sectionRepository.getAllByCourseId(courseId);
    }

    /**
     * 将课程转化为DTO
     *
     * @param course
     * @return
     */
    private ThCourseDTO course2DTO(Course course) {
        ThCourseDTO dto = new ThCourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setClassification(course.getClassification().getName());
        dto.setComments(commentRepository.commentsNum(course.getId()));
        dto.setDate(CommonUtils.dateFormatSimple(course.getCreateTime()));
        dto.setPicPath(course.getImagePath());
        dto.setStudy(userStudyRepository.studyNum(course.getId()));
        dto.setScore(course.getScore());
        dto.setNotice(course.getNotice());
        return dto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<ThCourseDTO> pageCourse(Pageable pageable) {
        return courseService.pageCourse(getTeacher(), pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Course getCourse(String courseId) {
        Course course = courseService.getCourse(courseId);
        return !course.getTeacher().getUsername().equals(getUsername()) ? null : course;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public TeacherDetailsDTO getInfo() {
        Teacher teacher = getTeacher();
        TeacherDetailsDTO detailsDTO = new TeacherDetailsDTO();
        detailsDTO.setId(teacher.getId());
        detailsDTO.setAvatar(teacher.getAvatar());
        detailsDTO.setDetails(teacher.getDetail());
        detailsDTO.setEmail(teacher.getEmail());
        detailsDTO.setGender(teacher.getGender().getName());
        detailsDTO.setPosition(teacher.getPosition());
        detailsDTO.setSignature(teacher.getSignature());
        detailsDTO.setUsername(teacher.getUsername());
        return detailsDTO;
    }
}
