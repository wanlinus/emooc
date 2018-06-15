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

package cn.wanlinus.emooc.enums;

/**
 * @author wanli
 * @date 2018-05-03 17:03
 */
public enum EmoocCourseGrade {
    /**
     * 用于描述课程等级的枚举变量
     */
    GRADE_BASIC("初级"),
    GRADE_PRIMARY("入门"),
    GRADE_MEDIUM("中级"),
    GRADE_ADVANCED("高级");
    private String description;

    EmoocCourseGrade(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
