/*
 * Copyright (C) 2018. - wanli <wanlinus@qq.com>
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

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 公共基础工具
 *
 * @author wanli
 * @date 2018-02-22 11:43
 */
public final class CommonUtils {

    private CommonUtils() {
        throw new AssertionError();
    }

    /**
     * 描述：获取Request
     *
     * @return request对象，可能为null
     * @author wanli
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != requestAttributes) {
            return requestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 生成UID的方法
     *
     * @return uid
     */
    public static String uid() {
        return "U" + System.currentTimeMillis();
    }

    /**
     * 生成教师ID
     *
     * @return tid
     */
    public static String tid() {
        return "T" + System.currentTimeMillis();
    }

    /**
     * 用户操作生成主键
     *
     * @return user operation log id
     */
    public static String userLogId() {
        return "LU" + System.currentTimeMillis();
    }

    /**
     * 教师操作生成主键
     *
     * @return teacher operation log id
     */
    public static String teacherLogId() {
        return "LT" + System.currentTimeMillis();
    }

    /**
     * 管理员操作主键
     *
     * @return admin operation log id
     */
    public static String adminLogId() {
        return "LA" + System.currentTimeMillis();
    }

    /**
     * 系统错误主键
     *
     * @return error id
     */
    public static String errorId() {
        return "Err" + System.currentTimeMillis();
    }


    /**
     * 获取设备名称
     *
     * @param request http请求
     * @return 设备简称
     */
    public static String getEquipment(HttpServletRequest request) {
        String eq = request.getHeader("User-Agent");
        return eq.substring(eq.indexOf("(") + 1, eq.indexOf(")"));
    }

    /**
     * md5摘要
     *
     * @param password 需要加密的原文
     * @return 返回md5摘要
     */
    public static String md5Encrypt(String password) {
        return DigestUtils.md5Hex(password);
    }

    /**
     * 自定义格式化日期
     *
     * @param date    需要格式化的日期
     * @param pattern 格式规则
     * @return 格式化后的字符串
     */
    public static String dateFormatCustom(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 简单格式化日期 模板为: 年-月-日 小时(24)
     *
     * @param date 需要格式化的日期
     * @return 格式化后的字符串
     */
    public static String dateFormatSimple(Date date) {
        return dateFormatCustom(date, "yyyy-MM-dd");
    }

    /**
     * 复杂格式化日期模 板为: 年-月-日 小时(24):分钟:秒钟
     *
     * @param date 需要格式化的日期
     * @return 格式化后的字符串
     */
    public static String dateFormatComplex(Date date) {
        return dateFormatCustom(date, "yyyy-MM-dd HH:mm:ss");
    }


}
