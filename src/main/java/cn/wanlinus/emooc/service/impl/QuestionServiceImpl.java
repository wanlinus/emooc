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

import cn.wanlinus.emooc.domain.Course;
import cn.wanlinus.emooc.domain.Question;
import cn.wanlinus.emooc.persistence.QuestionRepository;
import cn.wanlinus.emooc.service.QuestionService;
import cn.wanlinus.emooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    private UserService userService;

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
}
