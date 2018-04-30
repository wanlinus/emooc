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

package cn.wanlinus.emooc.persistence;

import cn.wanlinus.emooc.domain.CourseSection;

import java.util.List;

/**
 * @author wanli
 * @date 2018-04-15 13:59
 */
public interface CourseSectionRepository extends BaseRepository<CourseSection, String> {

    /**
     * 通过课程ID对Section计数
     *
     * @param courseId 课程ID
     * @return Section个数
     */
    Integer countCourseSectionByCourseId(String courseId);

    /**
     * 根据课程ID获取该课程的所有章节
     *
     * @param courseId 课程ID
     * @return 该课程的所有ID
     */
    List<CourseSection> getAllByCourseId(String courseId);
}
