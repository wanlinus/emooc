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

/**
 * @author wanli
 * @date 2018-04-15 02:30
 */
@Entity
@Table(name = "tb_course")
public class Course implements Serializable {
    @Id
    @Column(name = "course_id")
    private String id;

    @Column(name = "course_name")
    private String name;

    @Column(name = "course_tariff")
    private Double tariff;

    @Column(name = "course_grade")
    private Character grade;

    @Column(name = "course_duration")
    private Integer duration;

    @Column(name = "course_score")
    private Double score;

    @Column(name = "course_notice")
    private String notice;

    @Column(name = "course_wtcanlearn")
    private String wtcanlearn;

    @Column(name = "course_image")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "course_teach_id", referencedColumnName = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "course_classification_id", referencedColumnName = "classification_id")
    private CourseClassification classification;

    @ManyToOne
    @JoinColumn(name = "course_type_id", referencedColumnName = "type_id")
    private CourseType type;

    public Course() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTariff() {
        return tariff;
    }

    public void setTariff(Double tariff) {
        this.tariff = tariff;
    }

    public Character getGrade() {
        return grade;
    }

    public void setGrade(Character grade) {
        this.grade = grade;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getWtcanlearn() {
        return wtcanlearn;
    }

    public void setWtcanlearn(String wtcanlearn) {
        this.wtcanlearn = wtcanlearn;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public CourseClassification getClassification() {
        return classification;
    }

    public void setClassification(CourseClassification classification) {
        this.classification = classification;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

