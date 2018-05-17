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
import cn.wanlinus.emooc.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author wanli
 * @date 2018-02-22 10:24
 */
@Entity
@Table(name = "TB_USER")
public class User implements Serializable {
    @Id
    @Column(name = "USER_ID")
    private String id;

    @Column(name = "USER_USERNAME")
    private String username;

    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name = "USER_REALNAME")
    private String realname;

    @Column(name = "USER_EMAIL")
    private String email;

    @Column(name = "USER_TELEPHONE")
    private String telephone;

    @Column(name = "USER_POSITION")
    private String position;

    @Column(name = "USER_EXPERIENCE")
    private Integer experience;

    @Column(name = "USER_ADDRESS")
    private String address;

    @Column(name = "USER_BIRTHDAY")
    private Date birthday;

    @Column(name = "USER_LEARN_TIME")
    private Integer learnTime;

    @Column(name = "USER_INTEGRAL")
    private Integer integral;

    @Column(name = "USER_SIGNATURE")
    private String signature;

    @Column(name = "USER_AVATAR")
    private String avatar;

    @Column(name = "USER_GENDER")
    private Gender gender;

    @Column(name = "USER_BALANCE")
    private Double balance;

    @Column(name = "USER_REGISTER_TIME")
    private Date registerTime;

    @Column(name = "USER_STATUS")
    private UserStatus userStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<CourseComment> courseComments;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserStudy> studies;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Note> notes;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Question> questions;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Collection> collections;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<CourseScore> scores;

    public User() {
    }

    public User(String id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public List<CourseComment> getCourseComments() {
        return courseComments;
    }

    public void setCourseComments(List<CourseComment> courseComments) {
        this.courseComments = courseComments;
    }

    public List<UserStudy> getStudies() {
        return studies;
    }

    public void setStudies(List<UserStudy> studies) {
        this.studies = studies;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    public List<CourseScore> getScores() {
        return scores;
    }

    public void setScores(List<CourseScore> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", position='" + position + '\'' +
                ", experience=" + experience +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", learnTime=" + learnTime +
                ", integral=" + integral +
                ", signature='" + signature + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender=" + gender +
                ", balance=" + balance +
                ", registerTime=" + registerTime +
                ", userStatus=" + userStatus +
                '}';
    }
}
