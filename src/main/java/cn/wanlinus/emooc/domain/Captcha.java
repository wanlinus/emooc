package cn.wanlinus.emooc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Captcha captcha = (Captcha) o;
        return Objects.equals(id, captcha.id) &&
                Objects.equals(time, captcha.time) &&
                Objects.equals(user, captcha.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, time, user);
    }

    @Override
    public String toString() {
        return "Captcha{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", user=" + user +
                '}';
    }
}
