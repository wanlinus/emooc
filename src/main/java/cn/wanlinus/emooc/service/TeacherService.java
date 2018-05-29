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

package cn.wanlinus.emooc.service;

import cn.wanlinus.emooc.commons.ResultData;
import cn.wanlinus.emooc.domain.Course;
import cn.wanlinus.emooc.domain.CourseVideo;
import cn.wanlinus.emooc.domain.Teacher;
import cn.wanlinus.emooc.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * @author wanli
 * @date 2018-03-07 10:00
 */
public interface TeacherService {
    /**
     * 分页查询Teacher信息
     *
     * @param layuiPaginationDTO 分页信息
     * @return 分页查找数据
     */
    LayuiPaginationDataDTO<TeacherDetailsDTO> pageTeacher(LayuiPaginationDTO layuiPaginationDTO);

    /**
     * 添加教师
     *
     * @param dto 教师数据传输对象
     * @return 返回注册的老师
     */
    Teacher addTeacher(TeacherDetailsDTO dto);

    /**
     * 对所有教师计数
     *
     * @return 教师总人数
     */
    Long countTeachers();

    /**
     * 计算指定天数教师登陆数
     *
     * @param date 指定天数
     * @return 登陆量
     */
    Long countTeacherLogin(Date date);

    /**
     * 统计传入日期前days天用户登陆量
     *
     * @param date 传入日期
     * @param days 天数
     * @return 统计量
     */
    List<Long> teacherLoginStatistics(Date date, Integer days);


    /**
     * 计算指定日期教师注册量
     *
     * @param date 指定日期
     * @return 注册量
     */
    Long countTeacherRegister(Date date);

    /**
     * 添加课程
     *
     * @param dto 添加课程数据传输对象
     * @return 添加的课程ID
     */
    ResultData<String> addCourse(ThAddCourseDTO dto);

    /**
     * 获取教师顶置课程
     *
     * @return 顶置课程列表
     */
    List<ThCourseDTO> topCourses();

    /**
     * 教师分布课程分页显示
     *
     * @param pageable 分页信息
     * @return ThCourseDTO
     */
    List<ThCourseDTO> pageCourse(Pageable pageable);

    /**
     * 获取教师相应的课程
     *
     * @param courseId 课程ID
     * @return 相应的课程
     */
    Course getCourse(String courseId);

    /**
     * 获取教师基本信息
     *
     * @return 教师基本信息
     */
    TeacherDetailsDTO getInfo();

    /**
     * 获取课程详细信息
     *
     * @param courseId 课程ID
     * @return 课程传输对象
     */
    ThCourseDTO getCourseDetails(String courseId);

    /**
     * 传入日期前days天讲师注册统计
     *
     * @param date 指定日期
     * @param days 传入天数
     * @return 统计人数数组
     */
    List<Long> teacherRegisterStatistics(Date date, Integer days);


    /**
     * 添加课程章节
     *
     * @param dto 添加章节数据传输对象
     * @return 添加的课程
     */
    ResultData<String> addSection(SectionAddDTO dto);

    /**
     * 添加课程视频
     *
     * @param dto 课程
     * @return resultData
     */
    ResultData<String> addSectionVideo(CourseSectionVideoAddDTO dto);

    /**
     * 获取章节显示数据传输对象
     *
     * @param courseId 课程ID
     * @return SectionDisplayDTO
     */
    List<SectionDisplayDTO> getSectionsDisplay(String courseId);

    /**
     * 获取课程视频
     *
     * @param videoId 视频ID
     * @return 视频对象
     */
    CourseVideo getCourseVideo(String videoId);
}
