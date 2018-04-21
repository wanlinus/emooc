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
 * @date 2018-04-16 16:54
 */
public enum EmoocRole {
    /**
     * 用户权限配置
     */
    ROLE_USER("USER", "普通用户"),
    ROLE_TEACHER("TEACHER", "讲师"),
    ROLE_ADMIN("ADMIN", "管理员"),
    ROLE_UNKNOWN("UNKNOWN", "未知");

    private String desc;
    private String cn;

    EmoocRole(String desc, String cn) {
        this.desc = desc;
        this.cn = cn;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    @Override
    public String toString() {
        return "EmoocRole{" +
                "desc='" + desc + '\'' +
                ", cn='" + cn + '\'' +
                '}';
    }
}
