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

package cn.wanlinus.emooc.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author wanli
 * @date 2018-04-06 00:17
 */
public final class AuthUtils {
    public AuthUtils() {
        throw new AssertionError();
    }


    public static final String ROLE_USER = "[USER]";
    public static final String ROLE_TEACHER = "[TEACHER]";
    public static final String ROLE_ADMIN = "[ADMIN]";

    /**
     * 得到认证对象
     *
     * @return Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取登录用户名
     *
     * @return 用户名
     */
    public static String getUsername() {
        return getAuthentication().getName();
    }

    /**
     * 获取身份信息
     *
     * @return role
     */
    public static String getRole() {
        return getAuthentication().getAuthorities().toString();
    }
}
