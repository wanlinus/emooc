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
@Table(name = "tb_question")
public class Question implements Serializable {
    @Id
    @Column(name = "question_id")
    private String id;

    @Column(name = "question_time")
    private Date time;

    @Column(name = "question_detail")
    private String detail;

    @ManyToOne
    @JoinColumn(name = "question_user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_teacher_id", referencedColumnName = "teacher_id")
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
