package cn.wanlinus.emooc.domain;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wanli
 * @date 2018-04-15 02:45
 */
@Entity
@Table(name = "tb_course_section")
public class CourseSection implements Serializable {
    @Id
    @Column(name = "section_id")
    private String id;

    @Column(name = "section_name")
    private String name;

    @Column(name = "section_detail")
    private String detail;

    @ManyToOne
    @JoinColumn(name = "section_course_id", referencedColumnName = "course_id")
    private Course course;

    public CourseSection() {
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
