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
 * 用于描述用户状态的枚举类型
 *
 * @author wanli
 * @date 2018-02-22 10:48
 */
public enum UserStatus {
    /**
     * 用户状态
     */
    INACTIVE("用户未激活"),
    ACTIVATED("已激活"),
    DELETED("用户已删除"),
    DISABLED("用户禁用"),
    UNKNOWN("未知状态");

    private String name;

    UserStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
