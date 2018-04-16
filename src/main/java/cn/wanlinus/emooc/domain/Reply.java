package cn.wanlinus.emooc.domain;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wanli
 * @date 2018-04-15 02:48
 */
@Entity
@Table(name = "tb_reply")
public class Reply implements Serializable {
    @Id
    @Column(name = "reply_id")
    private String id;

    @Column(name = "reply_time")
    private Date time;

    @Column(name = "reply_detail")
    private String detail;

    @ManyToOne
    @JoinColumn(name = "reply_comment_id", referencedColumnName = "comment_id")
    private CourseComment comment;

    public Reply() {
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

    public CourseComment getComment() {
        return comment;
    }

    public void setComment(CourseComment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
