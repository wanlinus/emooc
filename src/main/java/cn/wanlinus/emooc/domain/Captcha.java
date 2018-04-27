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

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 验证码
 *
 * @author wanli
 * @date 2018-04-15 02:27
 */
@Entity
@Table(name = "TB_CAPTCHA")
public class Captcha implements Serializable {
    @Id
    @Column(name = "CAPTCHA_ID")
    private String id;

    @Column(name = "CAPTCHA_TIME")
    private Date time;

    @ManyToOne
    @JoinColumn(name = "CAPTCHA_USER_ID", referencedColumnName = "USER_ID")
    private User user;

    public Captcha() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
