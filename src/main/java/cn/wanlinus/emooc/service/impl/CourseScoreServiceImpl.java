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

package cn.wanlinus.emooc.service.impl;

import cn.wanlinus.emooc.domain.CourseScore;
import cn.wanlinus.emooc.persistence.CourseScoreRepository;
import cn.wanlinus.emooc.service.CourseScoreService;
import cn.wanlinus.emooc.service.EmoocLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wanli
 * @date 2018-05-17 21:05
 */
@Service
public class CourseScoreServiceImpl implements CourseScoreService {

    @Autowired
    private CourseScoreRepository scoreRepository;

    @Autowired
    private EmoocLogService logService;

    @Override
    public CourseScore saveScore(CourseScore score) {
        return scoreRepository.save(score);
    }

    @Override
    public List<Long> scoreStatistics(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(countScores(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public Long countScores(Date date) {
        return logService.countScores(date);
    }

    @Override
    public List<Map<String, Object>> courseScores() {
        return scoreRepository.avgCourseScores();
    }

    @Override
    public List<Map<String, Object>> courseScores(int offset, Integer pageSize) {
        return scoreRepository.avgCourseScores(offset, pageSize);
    }

    @Override
    public Long totalCourseScore() {
        return scoreRepository.totalCourseScore();
    }
}
