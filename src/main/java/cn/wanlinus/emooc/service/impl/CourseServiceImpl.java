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

import cn.wanlinus.emooc.domain.Course;
import cn.wanlinus.emooc.domain.CourseDirection;
import cn.wanlinus.emooc.domain.Teacher;
import cn.wanlinus.emooc.dto.ThAddCourseDTO;
import cn.wanlinus.emooc.dto.ThTopCoursesDTO;
import cn.wanlinus.emooc.persistence.*;
import cn.wanlinus.emooc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cn.wanlinus.emooc.utils.CommonUtils.cid;

/**
 * @author wanli
 * @date 2018-04-19 23:59
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseDirectionRepository courseDirectionRepository;

    @Autowired
    private CourseClassificationRepository classificationRepository;

    @Autowired
    private CourseTypeRepository courseTypeRepository;

    @Autowired
    private UserStudyRepository userStudyRepository;

    @Autowired
    private CourseCommentRepository commentRepository;

    @Override
    public List<CourseDirection> getAllCourseDirection() {
        return courseDirectionRepository.findAll();
    }

    @Override
    public Course saveCourse(Teacher teacher, ThAddCourseDTO dto) {
        Course course = new Course();
        course.setId(cid());
        course.setName(dto.getName());
        course.setTariff(dto.getTariff());
        course.setGrade(dto.getGrade());
        course.setDuration(0);
        course.setScore(0.0);
        course.setNotice(dto.getNotice());
        course.setWtcanlearn(dto.getWtcanlearn());
        course.setImagePath(dto.getPath());
        course.setTeacher(teacher);
        course.setCreateTime(new Date());
        course.setClassification(classificationRepository.findOne(String.valueOf(dto.getClassification())));
        course.setType(courseTypeRepository.findOne(String.valueOf(dto.getType())));
        return courseRepository.save(course);
    }

    @Override
    public List<Course> topCourse(Teacher teacher) {
        return courseRepository.findTopByTeacherId(teacher.getId());

    }

    @Override
    public List<ThTopCoursesDTO> pageCourse(Pageable pageable) {
        Page<Course> page = courseRepository.findAll(pageable);
        List<ThTopCoursesDTO> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Course c : page.getContent()) {
            ThTopCoursesDTO dto = new ThTopCoursesDTO();
            dto.setId(c.getId());
            dto.setName(c.getName());
            dto.setScore(c.getScore());
            dto.setPicPath(c.getImagePath());
            dto.setStudy(userStudyRepository.studyNum(c.getId()));
            dto.setComments(commentRepository.commentsNum(c.getId()));
            dto.setClassification(c.getClassification().getName());
            dto.setDate(sdf.format(c.getCreateTime()));
            list.add(dto);
            //TODO
        }
        return null;
    }
}
