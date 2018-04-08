package cn.wanlinus.emooc.dto;

import java.io.Serializable;

/**
 * 用户信息更新数据传输对象
 *
 * @author wanli
 * @date 2018-04-08 21:03
 */
public class UserUpdateDTO implements Serializable {

    private String id;

    private String username;

    private Integer gender;

    private String email;

    private String telephone;

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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

    @Override
    public String toString() {
        return "UserUpdateDTO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
