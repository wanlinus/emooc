package cn.wanlinus.emooc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wanli
 * @date 2018-03-06 16:09
 */
@Entity
@Table(name = "tb_user_log")
public class UserLog implements Serializable {
    @Id
    @Column(name = "opera_id")
    private String id;

    @Column(name = "opera_time")
    private Date time;

    @Column(name = "opera_detail")
    private String detail;

    @Column(name = "opera_ip")
    private String ip;

    @Column(name = "opera_equipment")
    private String equipment;

    @ManyToOne
    @JoinColumn(name = "opera_user_id", referencedColumnName = "user_id")
    private User user;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserLog{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", detail='" + detail + '\'' +
                ", ip='" + ip + '\'' +
                ", equipment='" + equipment + '\'' +
                ", user=" + user +
                '}';
    }
}
