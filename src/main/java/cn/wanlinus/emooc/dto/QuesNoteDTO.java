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

package cn.wanlinus.emooc.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 课程操作数据传输对象
 *
 * @author wanli
 * @date 2018-05-17 20:07
 */
public class QuesNoteDTO implements Serializable {

    /**
     * 日期数组
     */
    private List<String> date;
    /**
     * 问题统计量
     */
    private List<Long> questions;
    /**
     * 解答统计量
     */
    private List<Long> answers;
    /**
     * 笔记统计量
     */
    private List<Long> notes;
    /**
     * 评论统计量
     */
    private List<Long> comments;
    /**
     * 评分统计量
     */
    private List<Long> scores;

    public QuesNoteDTO() {
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<Long> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Long> questions) {
        this.questions = questions;
    }

    public List<Long> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Long> answers) {
        this.answers = answers;
    }

    public List<Long> getNotes() {
        return notes;
    }

    public void setNotes(List<Long> notes) {
        this.notes = notes;
    }

    public List<Long> getComments() {
        return comments;
    }

    public void setComments(List<Long> comments) {
        this.comments = comments;
    }

    public List<Long> getScores() {
        return scores;
    }

    public void setScores(List<Long> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "QuesNoteDTO{" +
                "date=" + date +
                ", questions=" + questions +
                ", answers=" + answers +
                ", notes=" + notes +
                ", comments=" + comments +
                ", scores=" + scores +
                '}';
    }
}
