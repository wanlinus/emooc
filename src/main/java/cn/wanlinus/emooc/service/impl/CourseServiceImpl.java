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

import cn.wanlinus.emooc.annotation.TeacherAnnotation;
import cn.wanlinus.emooc.domain.Course;
import cn.wanlinus.emooc.domain.CourseDirection;
import cn.wanlinus.emooc.domain.CourseSection;
import cn.wanlinus.emooc.domain.Teacher;
import cn.wanlinus.emooc.dto.SectionAddDTO;
import cn.wanlinus.emooc.dto.ThAddCourseDTO;
import cn.wanlinus.emooc.dto.ThCourseDTO;
import cn.wanlinus.emooc.enums.EmoocLogType;
import cn.wanlinus.emooc.persistence.*;
import cn.wanlinus.emooc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

import static cn.wanlinus.emooc.utils.CommonUtils.cid;
import static cn.wanlinus.emooc.utils.CommonUtils.csid;
import static cn.wanlinus.emooc.utils.CommonUtils.dateFormatSimple;

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
    private CourseSectionRepository sectionRepository;

    @Autowired
    private CourseClassificationRepository classificationRepository;

    @Autowired
    private CourseTypeRepository courseTypeRepository;

    @Autowired
    private CourseVideoRepository videoRepository;

    @Autowired
    private UserStudyRepository userStudyRepository;

    @Autowired
    private CourseCommentRepository commentRepository;

    @Autowired
    private EmoocLogRepository logRepository;

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
    public List<ThCourseDTO> pageCourse(Teacher teacher, Pageable pageable) {
        List<ThCourseDTO> dtoList = new ArrayList<>();
        List<Course> pages = courseRepository.pageCourses(teacher.getId(), pageable.getOffset(), pageable.getPageSize());
        if (pages != null && !pages.isEmpty()) {
            for (Course c : pages) {
                ThCourseDTO dto = new ThCourseDTO();
                dto.setId(c.getId());
                dto.setName(c.getName());
                dto.setClassification(c.getClassification().getName());
                dto.setComments(commentRepository.commentsNum(c.getId()));
                dto.setDate(dateFormatSimple(c.getCreateTime()));
                dto.setPicPath(c.getImagePath());
                dto.setScore(c.getScore());
                dto.setStudy(userStudyRepository.studyNum(c.getId()));
                dto.setNotice(c.getNotice());
                dtoList.add(dto);
            }
        } else {
            dtoList = null;
        }
        return dtoList;
    }


    @Override
    public Course getCourse(String courseId) {
        return courseRepository.findOne(courseId);
    }

    @Override
    public Long countCourses() {
        return courseRepository.count();

    }

    @Override
    public Long currentDayNewlyIncreased() {
        return courseRepository.courseNewlyIncreased(new Date());

    }

    @Override
    public List<Long> coursesAddStatistics(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(logRepository.countCourses(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    @TeacherAnnotation(type = EmoocLogType.TEACHER_ADD_SECTION)
    public CourseSection addSection(SectionAddDTO dto) {
        CourseSection section = new CourseSection();
        section.setId(csid());
        section.setIndex(sectionRepository.countCourseSectionByCourseId(dto.getCourseId()) + 1);
        section.setCourse(courseRepository.findOne(dto.getCourseId()));
        section.setDetail(dto.getDescription());
        section.setName(dto.getTitle());
        section.setCreateTime(new Date());
        return sectionRepository.save(section);
    }

    @Override
    public Long countCourseSectionVideos() {
        return videoRepository.count();
    }
}
