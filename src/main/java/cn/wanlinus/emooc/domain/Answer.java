package cn.wanlinus.emooc.domain;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 问题回答
 *
 * @author wanli
 * @date 2018-04-15 02:16
 */
@Entity
@Table(name = "tb_answer")
public class Answer implements Serializable {
    @Id
    @Column(name = "answer_id")
    private String id;

    @Column(name = "answer_detail")
    private String detail;

    @Column(name = "answer_time")
    private Date time;

    @ManyToOne
    @JoinColumn(name = "answer_question_id", referencedColumnName = "question_id")
    private Question question;

    public Answer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
