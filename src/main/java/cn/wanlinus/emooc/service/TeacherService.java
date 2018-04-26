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
    Long countTeachersLogin(Date date);

    /**
     * 添加课程
     *
     * @param dto      添加课程数据传输对象
     * @param filename 课程封面
     * @return 添加的课程
     */
    Course addCourse(ThAddCourseDTO dto, String filename);

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
}
