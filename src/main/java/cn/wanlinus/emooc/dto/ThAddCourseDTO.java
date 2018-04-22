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

import java.io.Serializable;

/**
 * 教师添加课程数据传输对象
 *
 * @author wanli
 * @date 2018-04-15 12:13
 */
public class ThAddCourseDTO implements Serializable {
    private String name;
    private Integer direction;
    private Integer classification;
    private Integer type;
    private Double tariff;
    private Character grade;
    private String notice;
    private String path;
    private String wtcanlearn;

    public ThAddCourseDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getClassification() {
        return classification;
    }

    public void setClassification(Integer classification) {
        this.classification = classification;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getTariff() {
        return tariff;
    }

    public void setTariff(Double tariff) {
        this.tariff = tariff;
    }

    public Character getGrade() {
        return grade;
    }

    public void setGrade(Character grade) {
        this.grade = grade;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getWtcanlearn() {
        return wtcanlearn;
    }

    public void setWtcanlearn(String wtcanlearn) {
        this.wtcanlearn = wtcanlearn;
    }

    @Override
    public String toString() {
        return "ThAddCourseDTO{" +
                "name='" + name + '\'' +
                ", direction=" + direction +
                ", classification=" + classification +
                ", type=" + type +
                ", tariff=" + tariff +
                ", grade=" + grade +
                ", notice='" + notice + '\'' +
                ", path='" + path + '\'' +
                ", wtcanlearn='" + wtcanlearn + '\'' +
                '}';
    }
}
