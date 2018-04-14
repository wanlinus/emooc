package cn.wanlinus.emooc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wanli
 * @date 2018-04-15 02:39
 */
@Entity
@Table(name = "tb_course_comment")
public class CourseComment implements Serializable {
    @Id
    @Column(name = "comment_id")
    private String id;

    @Column(name = "comment_time")
    private Date time;

    @Column(name = "comment_detail")
    private String detail;

    @Column(name = "comment_support")
    private Integer support;

    @ManyToOne
    @JoinColumn(name = "comment_user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_course_id", referencedColumnName = "course_id")
    private Course course;

    public CourseComment() {
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

    public Integer getSupport() {
        return support;
    }

    public void setSupport(Integer support) {
        this.support = support;
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
        return "CourseComment{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", detail='" + detail + '\'' +
                ", support=" + support +
                ", user=" + user +
                ", course=" + course +
                '}';
    }
}
