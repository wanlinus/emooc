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

package cn.wanlinus.emooc.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员主页用户注册, 讲师注册, 添加课程, 添加视频统计数据传输对象
 *
 * @author wanli
 * @date 2018-04-30 14:15
 */
public class StatisticsDTO implements Serializable {
    private List<String> date;

    private List<Long> admin;

    private List<Long> userRegister;
    private List<Long> userLogin;

    private List<Long> teacherRegister;
    private List<Long> teacherLogin;

    private List<Long> course;

    private List<Long> video;

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<Long> getAdmin() {
        return admin;
    }

    public void setAdmin(List<Long> admin) {
        this.admin = admin;
    }

    public List<Long> getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(List<Long> userRegister) {
        this.userRegister = userRegister;
    }

    public List<Long> getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(List<Long> userLogin) {
        this.userLogin = userLogin;
    }

    public List<Long> getTeacherRegister() {
        return teacherRegister;
    }

    public void setTeacherRegister(List<Long> teacherRegister) {
        this.teacherRegister = teacherRegister;
    }

    public List<Long> getTeacherLogin() {
        return teacherLogin;
    }

    public void setTeacherLogin(List<Long> teacherLogin) {
        this.teacherLogin = teacherLogin;
    }

    public List<Long> getCourse() {
        return course;
    }

    public void setCourse(List<Long> course) {
        this.course = course;
    }

    public List<Long> getVideo() {
        return video;
    }

    public void setVideo(List<Long> video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "StatisticsDTO{" +
                "date=" + date +
                ", admin=" + admin +
                ", userRegister=" + userRegister +
                ", userLogin=" + userLogin +
                ", teacherRegister=" + teacherRegister +
                ", teacherLogin=" + teacherLogin +
                ", course=" + course +
                ", video=" + video +
                '}';
    }
}
