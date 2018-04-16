package cn.wanlinus.emooc.domain;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wanli
 * @date 2018-04-15 02:48
 */
@Entity
@Table(name = "tb_user_study")
public class UserStudy implements Serializable {
    @Id
    @Column(name = "study_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "study_user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "study_course_id", referencedColumnName = "course_id")
    private Course course;

    public UserStudy() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return JSON.toJSONString(this);
    }
}
