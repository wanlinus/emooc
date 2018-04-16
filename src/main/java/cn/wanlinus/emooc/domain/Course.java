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

