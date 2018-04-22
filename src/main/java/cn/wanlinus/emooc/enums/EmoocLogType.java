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
 * @date 2018-04-21 13:41
 */
public enum EmoocLogType {
    /**
     * 描述
     */
    LOGIN("登陆"),
    USER_REGISTER("用户注册"),
    TEACHER_REGISTER("教师注册"),
    TEACHER_ADD_COURSE("教师添加课程"),
    TEACHER_ADD_VIDEO("教师添加视频"),
    UNKNOWN("未知");


    private String description;

    EmoocLogType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
