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
    TEACHER_ADD_COURSE("添加课程"),
    TEACHER_ADD_SECTION("添加章节"),
    TEACHER_ADD_VIDEO("添加视频"),
    USER_ACTIVATED("用户激活"),
    USER_ADD_COLLECTION("添加课程收藏"),
    USER_CANCEL_COLLECTION("取消课程收藏"),
    USER_ADD_QUESTION("提出问题"),
    USER_ADD_NOTE("添加笔记"),
    USER_ADD_COMMENT("添加评论"),
    USER_ADD_SCORE("对课程进行评分"),
    TEACHER_ADD_ANSWER("解答问题"),
    USER_CHANGE_INFORMATION("修改信息"),
    USER_CHANGE_AVATAR("修改头像"),
    USER_CHANGE_PASSWORD("修改密码"),
    USER_FORGET_AND_CHANGE_PASSWORD("忘记并重置密码"),
    USER_CHANGE_EMAIL("修改邮箱地址"),
    USER_CHANGE_PHONE("修改电话"),
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
