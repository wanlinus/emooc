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

import java.util.List;
import java.util.Map;

/**
 * @author wanli
 * @date 2018-05-18 12:59
 */
public interface CourseScoreCustomPersistence {
    /**
     * 获得所有课程的平均评分
     *
     * @return list容器 课程名 评分
     */
    List<Map<String, Object>> avgCourseScores();

    /**
     * 获得分页课程的平均评分
     *
     * @param offset   数据偏移量
     * @param pageSize 数据量
     * @return list容器 课程名 评分
     */
    List<Map<String, Object>> avgCourseScores(int offset, Integer pageSize);
}
