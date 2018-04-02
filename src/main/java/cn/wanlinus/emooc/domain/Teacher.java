package cn.wanlinus.emooc.domain;

import cn.wanlinus.emooc.enums.Gender;
import cn.wanlinus.emooc.enums.TeacherStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wanli
 * @date 2018-03-07 09:47
 */
@Entity
@Table(name = "tb_teacher")
public class Teacher {
    @Id
    @Column(name = "teacher_id")
    private String id;

    @Column(name = "teacher_username")
    private String username;

    @Column(name = "teacher_password")
    private String password;

    @Column(name = "teacher_gender")
    private Gender gender;

    @Column(name = "teacher_position")
    private String position;

    @Column(name = "teacher_detail")
    private String detail;

    @Column(name = "teacher_avatar")
    private String avatar;

    @Column(name = "teacher_signature")
    private String signature;

    @Column(name = "teacher_status")
    private TeacherStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public TeacherStatus getStatus() {
        return status;
    }

    public void setStatus(TeacherStatus status) {
        this.status = status;
    }
}
