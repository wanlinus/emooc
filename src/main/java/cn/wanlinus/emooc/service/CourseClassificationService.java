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

import cn.wanlinus.emooc.domain.CourseClassification;
import cn.wanlinus.emooc.domain.CourseDirection;

import java.util.List;

/**
 * @author wanli
 * @date 2018-04-22 21:12
 */
public interface CourseClassificationService {
    /**
     * 获取所有的课程分类
     *
     * @return 课程分类
     */
    List<CourseClassification> getClassifications();

    /**
     * 通过分类ID查找课程分类对象
     *
     * @param classificationId 课程分类ID
     * @return 相应的分类对象
     */
    CourseClassification get(String classificationId);

}
