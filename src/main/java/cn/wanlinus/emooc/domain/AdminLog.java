package cn.wanlinus.emooc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wanli
 * @date 2018-04-06 21:55
 */
@Entity
@Table(name = "tb_admin_log")
public class AdminLog implements Serializable {
    @Id
    @Column(name = "opera_id")
    private String id;

    @Column(name = "opera_detail")
    private String detail;

    @Column(name = "opera_result")
    private Boolean result;

    @Column(name = "opera_ip")
    private String ip;

    @Column(name = "opera_time")
    private Date date;

    @Column(name = "opera_equipment")
    private String equipment;

    @Column(name = "opera_comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "opera_admin_id")
    private Admin admin;

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

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "AdminLog{" +
                "id='" + id + '\'' +
                ", detail='" + detail + '\'' +
                ", result=" + result +
                ", ip='" + ip + '\'' +
                ", date=" + date +
                ", equipment='" + equipment + '\'' +
                ", comment='" + comment + '\'' +
                ", admin=" + admin +
                '}';
    }
}
