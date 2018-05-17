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
 * 课程方向圆饼图数据传输对象
 *
 * @author wanli
 * @date 2018-05-17 16:20
 */
public class CourseDirectionPieDTO implements Serializable {

    private String name;
    private Integer value;

    public CourseDirectionPieDTO() {
    }

    public CourseDirectionPieDTO(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CourseDirectionPieDTO{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
