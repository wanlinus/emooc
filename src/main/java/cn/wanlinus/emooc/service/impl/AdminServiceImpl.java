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

import cn.wanlinus.emooc.dto.CourseDirectionPieDTO;
import cn.wanlinus.emooc.dto.QuesNoteDTO;
import cn.wanlinus.emooc.dto.StatisticsDTO;
import cn.wanlinus.emooc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static cn.wanlinus.emooc.utils.CommonUtils.dateFormatSimple;

/**
 * @author wanli
 * @date 2018-04-21 14:33
 */
@Service
public class AdminServiceImpl implements AdminService {

    /**
     * 获取30天的数据
     */
    private static final int MONTH = 30;

    /**
     * 定义一周7天
     */
    private static final int WEEKDAY = 15;
    @Autowired
    private EmoocLogService logService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private CourseCommentService commentService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private CourseScoreService scoreService;

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Long countAdminLogin(Date date) {
        return logService.countAdminLogin(date);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Long> adminLoginStatistics(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(countAdminLogin(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    @Cacheable("admin:direction")
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<CourseDirectionPieDTO> courseDirectionPie() {
        List<CourseDirectionPieDTO> dtos = new ArrayList<>();
        List<Map<String, Object>> list = courseService.courseDirectionPie();
        for (Map<String, Object> map : list) {
            dtos.add(new CourseDirectionPieDTO(String.valueOf(map.get("dname")), Integer.parseInt(String.valueOf(map.get("counts")))));
        }
        return dtos;
    }

    @Override
    @Cacheable("admin:quesNote")
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public QuesNoteDTO quesNote() {
        QuesNoteDTO dto = new QuesNoteDTO();
        List<String> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < WEEKDAY; i++) {
            list.add(dateFormatSimple(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        Collections.reverse(list);
        dto.setDate(list);
        dto.setQuestions(questionService.questionStatistics(new Date(), WEEKDAY));
        dto.setAnswers(answerService.answerStatistics(new Date(), WEEKDAY));
        dto.setComments(commentService.commentStatistics(new Date(), WEEKDAY));
        dto.setNotes(noteService.noteStatistics(new Date(), WEEKDAY));
        dto.setScores(scoreService.scoreStatistics(new Date(), WEEKDAY));
        return dto;
    }

    @Override
    @Cacheable(value = "admin:statistics")
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public StatisticsDTO statistics() {
        StatisticsDTO dto = new StatisticsDTO();
        List<String> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < MONTH; i++) {
            list.add(dateFormatSimple(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }
        Collections.reverse(list);
        dto.setDate(list);
        dto.setAdmin(adminLoginStatistics(new Date(), MONTH));
        dto.setUserRegister(userService.userRegisterStatistics(new Date(), MONTH));
        dto.setUserLogin(userService.userLoginStatistics(new Date(), MONTH));
        dto.setTeacherRegister(teacherService.teacherRegisterStatistics(new Date(), MONTH));
        dto.setTeacherLogin(teacherService.teacherLoginStatistics(new Date(), MONTH));
        dto.setCourse(courseService.coursesAddStatistics(new Date(), MONTH));
        dto.setVideo(courseService.courseVideosStatistics(new Date(), MONTH));
        return dto;
    }
}
