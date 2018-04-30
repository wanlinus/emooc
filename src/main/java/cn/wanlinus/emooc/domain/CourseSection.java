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
 * @author wanli
 * @date 2018-04-15 02:45
 */
@Entity
@Table(name = "TB_COURSE_SECTION")
public class CourseSection implements Serializable {
    @Id
    @Column(name = "SECTION_ID")
    private String id;

    @Column(name = "SECTION_INDEX")
    private Integer index;

    @Column(name = "SECTION_NAME")
    private String name;

    @Column(name = "SECTION_DETAIL")
    private String detail;

    @Column(name = "SECTION_CREATE_TIME")
    private Date createTime;

    @ManyToOne
    @JoinColumn(name = "SECTION_COURSE_ID", referencedColumnName = "COURSE_ID")
    private Course course;

    public CourseSection() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
