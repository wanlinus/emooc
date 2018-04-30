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

import cn.wanlinus.emooc.domain.Course;
import cn.wanlinus.emooc.domain.CourseDirection;
import cn.wanlinus.emooc.domain.CourseSection;
import cn.wanlinus.emooc.domain.Teacher;
import cn.wanlinus.emooc.dto.SectionAddDTO;
import cn.wanlinus.emooc.dto.ThAddCourseDTO;
import cn.wanlinus.emooc.dto.ThCourseDTO;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * 课程服务接口
 *
 * @author wanli
 * @date 2018-04-19 23:57
 */
public interface CourseService {
    /**
     * 获取所有的课程方向
     *
     * @return 课程方向列表
     */
    List<CourseDirection> getAllCourseDirection();

    /**
     * 保存课程
     *
     * @param teacher 保存课程的教师
     * @param dto     保存课程的数据传输对象
     * @return 保存完成的课程对象
     */
    Course saveCourse(Teacher teacher, ThAddCourseDTO dto);

    /**
     * 查找指定讲师的顶置课程
     *
     * @param teacher 指定讲师
     * @return 课程列表
     */
    List<Course> topCourse(Teacher teacher);


    /**
     * 对相应教师的课程分页
     *
     * @param teacher  相应教师
     * @param pageable 分页信息
     * @return ThCourseDTO
     */
    List<ThCourseDTO> pageCourse(Teacher teacher, Pageable pageable);

    /**
     * 根据课程Id获取课程
     *
     * @param courseId 课程ID
     * @return 相应课程
     */
    Course getCourse(String courseId);

    /**
     * 对所有课程计数
     *
     * @return 课程数
     */
    Long countCourses();

    /**
     * 今日新增课程
     *
     * @return 新增课程数
     */
    Long currentDayNewlyIncreased();

    /**
     * 统计传入日期前days天课程添加数组
     *
     * @param date 传入日期
     * @param days 天数
     * @return 新增课程数组
     */
    List<Long> coursesAddStatistics(Date date, Integer days);

    /**
     * 添加课程章节
     *
     * @param dto 添加课程章节数据传输对象
     * @return 添加的课程
     */
    CourseSection addSection(SectionAddDTO dto);
}
