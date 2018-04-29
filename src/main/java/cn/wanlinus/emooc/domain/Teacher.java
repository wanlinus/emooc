/*
 * Copyright (C) 2018 - wanli <wanlinus@qq.com>
 *
 * This file is part of emooc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.wanlinus.emooc.domain;

import cn.wanlinus.emooc.enums.Gender;
import cn.wanlinus.emooc.enums.TeacherStatus;
import com.alibaba.fastjson.JSON;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author wanli
 * @date 2018-03-07 09:47
 */
@Entity
@Table(name = "TB_TEACHER")
public class Teacher implements Serializable {
    @Id
    @Column(name = "TEACHER_ID")
    private String id;

    @Column(name = "TEACHER_USERNAME")
    private String username;

    @Column(name = "TEACHER_PASSWORD")
    private String password;

    @Column(name = "TEACHER_GENDER")
    private Gender gender;

    @Column(name = "TEACHER_EMAIL")
    private String email;

    @Column(name = "TEACHER_POSITION")
    private String position;

    @Column(name = "TEACHER_DETAIL")
    private String detail;

    @Column(name = "TEACHER_AVATAR")
    private String avatar;

    @Column(name = "TEACHER_SIGNATURE")
    private String signature;

    @Column(name = "TEACHER_STATUS")
    private TeacherStatus status;

    public Teacher() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
