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

import cn.wanlinus.emooc.enums.EmoocCourseGrade;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author wanli
 * @date 2018-04-15 02:30
 */
@Entity
@Table(name = "TB_COURSE")
public class Course implements Serializable {
    @Id
    @Column(name = "COURSE_ID")
    private String id;

    @Column(name = "COURSE_NAME")
    private String name;

    @Column(name = "COURSE_TARIFF")
    private Double tariff;

    @Column(name = "COURSE_GRADE")
    private EmoocCourseGrade grade;

    @Column(name = "COURSE_DURATION")
    private Integer duration;

    @Column(name = "COURSE_SCORE")
    private Double score;

    @Column(name = "COURSE_NOTICE")
    private String notice;

    @Column(name = "COURSE_WTCANLEARN")
    private String wtcanlearn;

    @Column(name = "COURSE_IMAGE")
    private String imagePath;

    @Column(name = "COURSE_CREATE_TIME")
    private Date createTime;

    @ManyToOne
    @JoinColumn(name = "COURSE_TEACH_ID", referencedColumnName = "TEACHER_ID")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "COURSE_CLASSIFICATION_ID", referencedColumnName = "CLASSIFICATION_ID")
    private CourseClassification classification;

    @ManyToOne
    @JoinColumn(name = "COURSE_TYPE_ID", referencedColumnName = "TYPE_ID")
    private CourseType type;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<CourseSection> sections;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<CourseComment> courseComments;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<UserStudy> studies;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Note> notes;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Collection> collections;

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

    public EmoocCourseGrade getGrade() {
        return grade;
    }

    public void setGrade(EmoocCourseGrade grade) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public List<CourseSection> getSections() {
        return sections;
    }

    public void setSections(List<CourseSection> sections) {
        this.sections = sections;
    }

    public List<CourseComment> getCourseComments() {
        return courseComments;
    }

    public void setCourseComments(List<CourseComment> courseComments) {
        this.courseComments = courseComments;
    }

    public List<UserStudy> getStudies() {
        return studies;
    }

    public void setStudies(List<UserStudy> studies) {
        this.studies = studies;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

