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

import cn.wanlinus.emooc.domain.CourseScore;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wanli
 * @date 2018-05-17 21:04
 */
public interface CourseScoreService {

    /**
     * 保存课程评分
     *
     * @param score 课程评分对象
     * @return 保存的评分对象
     */
    CourseScore saveScore(CourseScore score);

    /**
     * 统计传入日期评分统计量
     *
     * @param date 相应日期
     * @param days 前多少天
     * @return 统计量
     */
    List<Long> scoreStatistics(Date date, int days);

    /**
     * 统计指定日期评分量
     *
     * @param date 指定日期
     * @return 统计量
     */
    Long countScores(Date date);

    /**
     * 所有课程的平均分容器
     *
     * @return list容器
     */
    List<Map<String, Object>> courseScores();

    /**
     * 所又课程平局分分页计算平均分
     *
     * @param offset   偏移量
     * @param pageSize 每页数据量
     * @return
     */
    List<Map<String, Object>> courseScores(int offset, Integer pageSize);

    /**
     * 对所有评分课程进行计数
     *
     * @return 统计量
     */
    Long totalCourseScore();


}
