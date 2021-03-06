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
import cn.wanlinus.emooc.enums.EmoocLogType;
import cn.wanlinus.emooc.persistence.NoteRepository;
import cn.wanlinus.emooc.service.EmoocLogService;
import cn.wanlinus.emooc.service.NoteService;
import cn.wanlinus.emooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static cn.wanlinus.emooc.utils.CommonUtils.nid;

/**
 * @author wanli
 * @date 2018-05-17 14:40
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmoocLogService logService;

    @Override
    @UserAnnotation(type = EmoocLogType.USER_ADD_NOTE)
    public Note addNote(String msg, Course course) {
        Note note = new Note();
        note.setId(nid());
        note.setDetail(msg);
        note.setTime(new Date());
        note.setCourse(course);
        note.setUser(userService.getCurrentUser());
        return noteRepository.save(note);
    }

    @Override
    public List<Long> noteStatistics(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(countNotes(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public Long countNotes(Date date) {
        return logService.countNotes(date);
    }


}
