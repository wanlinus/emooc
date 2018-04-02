package cn.wanlinus.emooc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wanli
 * @date 2018-02-22 11:00
 */
@Entity
@Table(name = "tb_admin")
public class Admin {
    @Id
    @Column(name = "admin_id")
    private String id;

    @Column(name = "admin_name")
    private String name;

    @Column(name = "admin_password")
    private String password;

    @Column(name = "admin_email")
    private String email;

    public Admin() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
