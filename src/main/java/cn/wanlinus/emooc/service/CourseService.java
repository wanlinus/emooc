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
import cn.wanlinus.emooc.domain.CourseType;
import cn.wanlinus.emooc.domain.Teacher;
import cn.wanlinus.emooc.dto.ThAddCourseDTO;

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
}
