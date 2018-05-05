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

package cn.wanlinus.emooc.persistence.custom;

import cn.wanlinus.emooc.domain.Course;

import java.util.List;

/**
 * @author wanli
 * @date 2018-05-05 15:02
 */
public interface CourseCustomPersistence extends BaseCustomPersistence {
    /**
     * 对课程进行筛选分页
     *
     * @param pageSize         每页条数
     * @param page             第几页
     * @param directionId      方向Id
     * @param classificationId 分类Id
     * @return 课程集合
     */
    List<Course> pageCourses(Integer pageSize, Integer page, String directionId, String classificationId);
}
