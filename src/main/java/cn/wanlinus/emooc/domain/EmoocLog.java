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

import cn.wanlinus.emooc.enums.EmoocLogType;
import cn.wanlinus.emooc.enums.EmoocRole;
import com.alibaba.fastjson.JSON;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 日志表
 *
 * @author wanli
 * @date 2018-04-16 16:30
 */
@Entity
@Table(name = "tb_log")
public class EmoocLog implements Serializable {
    @Id
    @Column(name = "log_id")
    private String id;

    @Column(name = "log_who")
    private String who;

    @Column(name = "log_role")
    private EmoocRole role;

    @Column(name = "log_time")
    private Date time;

    @Column(name = "log_type")
    private EmoocLogType type;

    @Column(name = "log_result")
    private Boolean result;

    @Column(name = "log_ip")
    private String ip;

    @Column(name = "log_equipment")
    private String equipment;

    @Column(name = "log_comment")
    private String comment;

    public EmoocLog() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public EmoocRole getRole() {
        return role;
    }

    public void setRole(EmoocRole role) {
        this.role = role;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public EmoocLogType getType() {
        return type;
    }

    public void setType(EmoocLogType type) {
        this.type = type;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
