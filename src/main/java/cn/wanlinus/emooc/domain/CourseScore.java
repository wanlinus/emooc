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

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wanli
 * @date 2018-05-17 20:57
 */
@Entity
@Table(name = "TB_COURSE_SCORE")
public class CourseScore implements Serializable {

    @Id
    @Column(name = "SCORE_ID")
    private String id;

    @Column(name = "SCORE_GRADE")
    private Double grade;

    @Column(name = "SCORE_WISHES")
    private String wishes;

    @Column(name = "SCORE_TIME")
    private Date time;

    @ManyToOne
    @JoinColumn(name = "SCORE_USER_ID", referencedColumnName = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "SCORE_COURSE_ID", referencedColumnName = "COURSE_ID")
    private Course course;

    public CourseScore() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getWishes() {
        return wishes;
    }

    public void setWishes(String wishes) {
        this.wishes = wishes;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "CourseScore{" +
                "id='" + id + '\'' +
                ", grade=" + grade +
                ", wishes='" + wishes + '\'' +
                ", time=" + time +
                ", user=" + user +
                ", course=" + course +
                '}';
    }
}
