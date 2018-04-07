package cn.wanlinus.emooc.dto;

import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.utils.CommonUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户详情数据传输对象
 *
 * @author wanli
 * @date 2018-04-07 19:34
 */
public class UserDetailsDTO implements Serializable {

    private String id;

    private String username;

    private String realname;

    private String email;

    private String telephone;

    private String position;

    private Integer experience;

    private String address;

    private Date birthday;

    private Integer learnTime;

    private Integer integral;

    private String signature;

    private String avatar;

    private String gender;

    private Double balance;

    private String registerTime;

    private String userStatus;

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.realname = user.getRealname();
        this.email = user.getEmail();
        this.telephone = user.getTelephone();
        this.position = user.getPosition();
        this.experience = user.getExperience();
        this.address = user.getAddress();
        this.birthday = user.getBirthday();
        this.learnTime = user.getLearnTime();
        this.integral = user.getIntegral();
        this.signature = user.getSignature();
        this.avatar = user.getAvatar();
        this.gender = user.getGender().getName();
        this.balance = user.getBalance();
        this.registerTime = CommonUtils.dateFormatSimple(user.getRegisterTime());
        this.userStatus = user.getUserStatus() != null ? user.getUserStatus().getName() : "未知";
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "UserDetailsDTO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
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
                ", gender='" + gender + '\'' +
                ", balance=" + balance +
                ", registerTime=" + registerTime +
                ", userStatus='" + userStatus + '\'' +
                '}';
    }
}
