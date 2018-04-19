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

import cn.wanlinus.emooc.domain.User;

import java.io.Serializable;

/**
 * 用户简要信息数据传输对象
 *
 * @author wanli
 * @date 2018-04-07 20:33
 */
public class UserSimpleDTO implements Serializable {
    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别
     */
    private String gender;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 经验
     */
    private Integer experience;

    /**
     * 学习总时长
     */
    private Integer learnTime;

    /**
     * 积分
     */
    private Integer integral;

    /**
     * 余额
     */
    private Double balance;

    /**
     * 用户状态
     */
    private String status;

    public UserSimpleDTO() {
    }

    public UserSimpleDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.gender = user.getGender() != null ? user.getGender().getName() : "未知";
        this.telephone = user.getTelephone();
        this.email = user.getEmail();
        this.experience = user.getExperience();
        this.learnTime = user.getLearnTime();
        this.integral = user.getIntegral();
        this.balance = user.getBalance();
        this.status = user.getUserStatus() != null ? user.getUserStatus().getName() : "未知";
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLearnTime() {
        return learnTime;
    }

    public void setLearnTime(Integer learnTime) {
        this.learnTime = learnTime;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserSimpleDTO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", experience=" + experience +
                ", status='" + status + '\'' +
                '}';
    }
}
