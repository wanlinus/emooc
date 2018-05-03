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
import cn.wanlinus.emooc.domain.*;
import cn.wanlinus.emooc.dto.CourseSectionVideoAddDTO;
import cn.wanlinus.emooc.dto.SectionAddDTO;
import cn.wanlinus.emooc.dto.ThAddCourseDTO;
import cn.wanlinus.emooc.dto.ThCourseDTO;
import cn.wanlinus.emooc.enums.EmoocCourseGrade;
import cn.wanlinus.emooc.enums.EmoocLogType;
import cn.wanlinus.emooc.persistence.*;
import cn.wanlinus.emooc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static cn.wanlinus.emooc.utils.CommonUtils.*;

/**
 * @author wanli
 * @date 2018-04-19 23:59
 */
@Service
public class CourseServiceImpl implements CourseService {

    /**
     * 课程持久对象
     */
    @Autowired
    private CourseRepository courseRepository;

    /**
     * 课程章节服务对象
     */
    @Autowired
    private CourseSectionService sectionService;

    /**
     * 课程方向服务对象
     */
    @Autowired
    private CourseDirectionService directionService;

    /**
     * 课程类型服务对象
     */
    @Autowired
    private CourseClassificationService classificationService;

    /**
     * 课程视频服务对象
     */
    @Autowired
    private CourseVideoService videoService;

    /**
     * 课程类型服务对象
     */
    @Autowired
    private CourseTypeService typeService;

    /**
     * 用户学习服务对象
     */
    @Autowired
    private UserStudyService userStudyService;

    /**
     * 评论服务对象
     */
    @Autowired
    private CourseCommentService commentService;

    /**
     * 日志服务对象
     */
    @Autowired
    private EmoocLogService logService;


    @Override
    public List<CourseDirection> getAllCourseDirection() {
        return directionService.getAllDirections();
    }

    @Override
    public Course saveCourse(Teacher teacher, ThAddCourseDTO dto) {
        Course course = new Course();
        course.setId(cid());
        course.setName(dto.getName());
        course.setTariff(dto.getTariff());
        course.setGrade(EmoocCourseGrade.values()[dto.getGrade()]);
        course.setDuration(0);
        course.setScore(0.0);
        course.setNotice(dto.getNotice());
        course.setWtcanlearn(dto.getWtcanlearn());
        course.setImagePath(dto.getPath());
        course.setTeacher(teacher);
        course.setCreateTime(new Date());
        course.setClassification(classificationService.get(String.valueOf(dto.getClassification())));
        course.setType(typeService.get(String.valueOf(dto.getType())));
        return courseRepository.save(course);
    }

    @Override
    public List<Course> topCourse(Teacher teacher) {
        return courseRepository.findTopByTeacherId(teacher.getId(), 5);

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
                dto.setComments(commentService.count(c.getId()));
                dto.setDate(dateFormatSimple(c.getCreateTime()));
                dto.setPicPath(c.getImagePath());
                dto.setScore(c.getScore());
                dto.setStudy(userStudyService.countStudies(c.getId()));
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
            list.add(logService.countCourseLogs(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    @TeacherAnnotation(type = EmoocLogType.TEACHER_ADD_SECTION)
    @Transactional(rollbackFor = Exception.class)
    public CourseSection addSection(SectionAddDTO dto) {
        CourseSection section = new CourseSection();
        section.setId(csid());
        section.setIndex(sectionService.countCourseSection(dto.getCourseId()) + 1);
        section.setCourse(courseRepository.findOne(dto.getCourseId()));
        section.setDetail(dto.getDescription());
        section.setName(dto.getTitle());
        section.setCreateTime(new Date());
        return sectionService.save(section);
    }

    @Override
    public Long countCourseSectionVideos() {
        return videoService.countVideos();
    }

    @Override
    public CourseVideo addSectionVideo(CourseSectionVideoAddDTO dto) {
        CourseVideo video = new CourseVideo();
        video.setId(csvid());
        video.setName(dto.getName());
        //先设置为0,以后能够处理视频文件再细化
        video.setDuration(0);
        video.setPath(dto.getVideoPath());
        video.setSha1(dto.getSha1());
        video.setCreateTime(new Date());
        CourseSection section = sectionService.find(dto.getSectionId());
        video.setSection(section);
        return videoService.saveVideo(video);
    }

    @Override
    public List<Long> courseVideosStatistics(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(logService.countVideoLogs(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public Long currentDayVideoNewlyIncreased() {
        return videoService.countVideos(new Date());
    }

    @Override
    public CourseVideo getCourseVideo(String videoId) {
        return videoService.findVideo(videoId);
    }

    @Override
    public List<CourseClassification> getAllClassifications() {
        return classificationService.getClassifications();
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getAllCoursesDescDate() {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return courseRepository.findAll(sort);
    }

    @Override
    public CourseDirection getCourseDirection(String directionId) {
        return directionService.getDirection(directionId);
    }

    @Override
    public List<Course> getTopCourses(String teacherId, Integer number) {
        return courseRepository.findTopByTeacherId(teacherId, number);
    }
}
