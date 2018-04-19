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
import java.util.List;

/**
 * 课程大方向
 *
 * @author wanli
 * @date 2018-04-15 02:43
 */
@Entity
@Table(name = "tb_course_direction", uniqueConstraints = {
        @UniqueConstraint(columnNames = "direction_id")})
public class CourseDirection implements Serializable {
    @Id
    @Column(name = "direction_id")
    private String id;

    @Column(name = "direction_name")
    private String name;

    @OneToMany
    @JoinColumn(name = "classification_id", referencedColumnName = "direction_id")
    private List<CourseClassification> classifications;

    public CourseDirection() {
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

    public List<CourseClassification> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<CourseClassification> classifications) {
        this.classifications = classifications;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
