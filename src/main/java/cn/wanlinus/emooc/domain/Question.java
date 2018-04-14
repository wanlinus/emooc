package cn.wanlinus.emooc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(time, question.time) &&
                Objects.equals(detail, question.detail) &&
                Objects.equals(user, question.user) &&
                Objects.equals(teacher, question.teacher);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, time, detail, user, teacher);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", detail='" + detail + '\'' +
                ", user=" + user +
                ", teacher=" + teacher +
                '}';
    }
}
