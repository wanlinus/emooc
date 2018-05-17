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

import cn.wanlinus.emooc.domain.Course;
import cn.wanlinus.emooc.domain.Note;

import java.util.Date;
import java.util.List;

/**
 * @author wanli
 * @date 2018-05-17 14:40
 */
public interface NoteService {

    /**
     * 添加笔记
     *
     * @param note   笔记内容
     * @param course 记笔记的课程对象
     * @return 笔记对象本身
     */
    Note addNote(String note, Course course);

    /**
     * 统计传入日期笔记统计
     *
     * @param date 相应日期
     * @param days 前多少天
     * @return 统计量
     */
    List<Long> noteStatistics(Date date, int days);

    /**
     * 对指定日期笔记进行统计
     *
     * @param date 指定日期
     * @return 统计量
     */
    Long countNotes(Date date);
}
