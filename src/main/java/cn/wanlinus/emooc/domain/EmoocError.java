package cn.wanlinus.emooc.domain;

import com.alibaba.fastjson.JSON;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wanli
 * @date 2018-04-06 22:58
 */
@Entity
@Table(name = "tb_error")
public class EmoocError implements Serializable {
    @Id
    @Column(name = "error_id")
    private String id;

    @Column(name = "error_who")
    private String who;

    @Column(name = "error_time")
    private Date time;

    @Column(name = "error_details")
    private String details;


    public EmoocError() {
    }

    public EmoocError(String id, String who, Date time, String details) {
        this.id = id;
        this.who = who;
        this.time = time;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
