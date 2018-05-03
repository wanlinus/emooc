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

import cn.wanlinus.emooc.domain.CourseSection;

/**
 * @author wanli
 * @date 2018-05-03 18:52
 */
public interface CourseSectionService {
    /**
     * 通过课程ID计数相应课程
     *
     * @param courseId 课程ID
     * @return 相应统计量
     */
    Integer countCourseSection(String courseId);

    /**
     * 保存课程章节
     *
     * @param section 需要保存的章节
     * @return 保存的章节
     */
    CourseSection save(CourseSection section);

    /**
     * 通过章节Id查找相应章节
     *
     * @param sectionId 指定章节Id
     * @return 返回查找到的章节
     */
    CourseSection find(String sectionId);
}
