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

package cn.wanlinus.emooc.persistence.impl;

import cn.wanlinus.emooc.persistence.custom.CourseScoreCustomPersistence;

import java.util.List;
import java.util.Map;

/**
 * 课程评分自定义查询事实现
 *
 * @author wanli
 * @date 2018-05-18 15:24
 */
public class CourseScoreRepositoryImpl extends BaseCustomPersistenceImpl implements CourseScoreCustomPersistence {

    @Override
    public List<Map<String, Object>> avgCourseScores() {
        final String sql = "SELECT s.SCORE_COURSE_ID AS courseId, CONVERT(AVG(s.SCORE_GRADE),DECIMAL(8, 2)) AS avgScore " +
                "FROM TB_COURSE_SCORE s GROUP BY s.SCORE_COURSE_ID;";
        return nativeQuery(sql);
    }
}
