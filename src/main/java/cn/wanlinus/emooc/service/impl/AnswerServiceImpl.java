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

import cn.wanlinus.emooc.persistence.AnswerRepository;
import cn.wanlinus.emooc.service.AnswerService;
import cn.wanlinus.emooc.service.EmoocLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wanli
 * @date 2018-05-17 20:26
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private EmoocLogService logService;


    @Override
    public List<Long> answerStatistics(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(countAnswers(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public Long countAnswers(Date date) {
        return logService.countAnswers(date);
    }
}
