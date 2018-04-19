/*
 * Copyright (C) 2018. - wanli <wanlinus@qq.com>
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

package cn.wanlinus.emooc.dto;

import cn.wanlinus.emooc.domain.Teacher;

import java.io.Serializable;

/**
 * @author wanli
 * @date 2018-04-06 13:51
 */
public class TeacherDetailsDTO implements Serializable {
    private String id;
    private String username;
    private String gender;
    private String password;
    private String position;
    private String details;
    private String avatar;
    private String signature;
    private String status;
    private String email;

    public TeacherDetailsDTO() {
    }

    public TeacherDetailsDTO(String id, String username, String gender, String email, String position, String details, String avatar, String signature, String status) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.position = position;
        this.details = details;
        this.avatar = avatar;
        this.email = email;
        this.signature = signature;
        this.status = status;
    }

    public TeacherDetailsDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.username = teacher.getUsername();
        this.gender = teacher.getGender().getName();
        this.position = teacher.getPosition();
        this.details = teacher.getDetail();
        this.avatar = teacher.getAvatar();
        this.email = teacher.getEmail();
        this.signature = teacher.getSignature();
        this.status = teacher.getStatus().getName();
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "TeacherDetailsDTO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", position='" + position + '\'' +
                ", details='" + details + '\'' +
                ", avatar='" + avatar + '\'' +
                ", signature='" + signature + '\'' +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
