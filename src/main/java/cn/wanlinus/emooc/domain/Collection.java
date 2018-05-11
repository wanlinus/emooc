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

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户收藏课程对象
 *
 * @author wanli
 * @date 2018/5/11
 */
@Entity
@Table(name = "tb_collection")
public class Collection implements Serializable {
    @Id
    @Column(name = "COLLECTION_ID")
    private String id;

    @Column(name = "COLLECTION_TIME")
    private Date time;

    @Column(name = "COLLECTION_STATUS")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "COLLECTION_USER_ID", referencedColumnName = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "COLLECTION_COURSE_ID", referencedColumnName = "COURSE_ID")
    private Course course;

    public Collection() {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", status=" + status +
                ", user=" + user +
                ", course=" + course +
                '}';
    }
}
