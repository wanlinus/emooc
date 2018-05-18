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

import cn.wanlinus.emooc.annotation.UserAnnotation;
import cn.wanlinus.emooc.domain.Course;
import cn.wanlinus.emooc.domain.Note;
import cn.wanlinus.emooc.domain.Question;
import cn.wanlinus.emooc.enums.EmoocLogType;
import cn.wanlinus.emooc.persistence.QuestionRepository;
import cn.wanlinus.emooc.service.EmoocLogService;
import cn.wanlinus.emooc.service.NoteService;
import cn.wanlinus.emooc.service.QuestionService;
import cn.wanlinus.emooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static cn.wanlinus.emooc.utils.CommonUtils.nid;
import static cn.wanlinus.emooc.utils.CommonUtils.qid;

/**
 * @author wanli
 * @date 2018-05-16 22:09
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmoocLogService logService;

    @Override
    public Question addQuestion(String msg, Course course) {
        Question question = new Question();
        question.setId(qid());
        question.setUser(userService.getCurrentUser());
        question.setDetail(msg);
        question.setTime(new Date());
        question.setCourse(course);
        return questionRepository.save(question);
    }


    @Override
    public List<Long> questionStatistics(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(countQuestions(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        Collections.reverse(list);
        return list;

    }

    @Override
    public Long countQuestions(Date date) {
        return logService.countQuestions(date);
    }

}
