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

package cn.wanlinus.emooc.domain;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 学生对来时问问题
 *
 * @author wanli
 * @date 2018-04-15 02:18
 */
@Entity
@Table(name = "TB_QUESTION")
public class Question implements Serializable {
    @Id
    @Column(name = "QUESTION_ID")
    private String id;

    @Column(name = "QUESTION_TIME")
    private Date time;

    @Column(name = "QUESTION_DETAIL")
    private String detail;

    @ManyToOne
    @JoinColumn(name = "QUESTION_USER_ID", referencedColumnName = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "QUESTION_TEACHER_ID", referencedColumnName = "TEACHER_ID")
    private Teacher teacher;

    public Question() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
