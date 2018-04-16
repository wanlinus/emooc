package cn.wanlinus.emooc.domain;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 验证码
 *
 * @author wanli
 * @date 2018-04-15 02:27
 */
@Entity
@Table(name = "tb_captcha")
public class Captcha implements Serializable {
    @Id
    @Column(name = "captcha_id")
    private String id;

    @Column(name = "captcha_time")
    private Date time;

    @ManyToOne
    @JoinColumn(name = "captcha_user_id", referencedColumnName = "user_id")
    private User user;

    public Captcha() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
