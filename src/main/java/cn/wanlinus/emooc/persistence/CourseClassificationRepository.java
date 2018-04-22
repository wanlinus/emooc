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

import cn.wanlinus.emooc.domain.CourseClassification;

import java.util.List;

/**
 * @author wanli
 * @date 2018-04-15 13:54
 */
public interface CourseClassificationRepository extends BaseRepository<CourseClassification, String> {
    /**
     * 更具课程方向ID查询课程分类
     *
     * @param directionId 课程方向ID
     * @return 课程分类
     */
    List<CourseClassification> findByDirectionId(String directionId);
}
