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

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
     * 保存文件
     *
     * @param multipartFile 上传的文件
     * @param rootPath      root目录
     * @param filePath      文件子目录
     * @return 文件路径
     * @throws IOException 读写异常
     */
    public static String saveFile(MultipartFile multipartFile, String rootPath, String filePath) throws IOException {
        String filename = preFilename() + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        File file = new File(rootPath + filePath + filename);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file);
        FileInputStream fs = (FileInputStream) multipartFile.getInputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fs.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        fs.close();
        return filePath + filename;
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
     * 生成验证码码Id
     *
     * @return capid
     */
    public static String capid() {
        return "CAP" + System.currentTimeMillis();
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
     * 生成课程ID
     *
     * @return cid
     */
    public static String cid() {
        return "C" + System.currentTimeMillis();
    }

    /**
     * 收藏Id
     *
     * @return colid
     */
    public static String colid() {
        return "COL" + System.currentTimeMillis();
    }

    /**
     * 生成课程章节ID
     *
     * @return csid
     */
    public static String csid() {
        return "CS" + System.currentTimeMillis();
    }

    /**
     * 生成课程章节视频ID
     *
     * @return csvid
     */
    public static String csvid() {
        return "CSV" + System.currentTimeMillis();
    }

    /**
     * 问题ID
     *
     * @return qid
     */
    public static String qid() {
        return "Q" + System.currentTimeMillis();
    }

    /**
     * 笔记ID
     *
     * @return nid
     */
    public static String nid() {
        return "NOTE" + System.currentTimeMillis();
    }

    /**
     * 课程评分ID
     *
     * @return sid
     */
    public static String sid() {
        return "Score" + System.currentTimeMillis();
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
     * 调度任务ID
     *
     * @return task id
     */
    public static String taskid() {
        return "Task" + System.currentTimeMillis();
    }

    /**
     * 获取文件名
     *
     * @return 文件名
     */
    public static String preFilename() {
        return "emooc-" + UUID.randomUUID();
    }


    /**
     * 获取设备名称
     *
     * @param request http请求
     * @return 设备简称
     */
    public static String getEquipment(HttpServletRequest request) {
        return request.getHeader("User-Agent");
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

    /**
     * 获取今日零点时间 如:现在是2018-4-21 12:51:32 转化后2018-4-21 00:00:00
     *
     * @param date 传入日期
     * @return 当天第一秒钟
     */
    public static Date startDate(final Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        Date tmp = null;
        try {
            tmp = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    /**
     * 返回传入日期当天最后一秒
     *
     * @param date 传入日期
     * @return 当天最后一秒
     */
    public static Date endDate(final Date date) {
        Date tmp = startDate(date);
        return new Date(tmp.getTime() + 24 * 60 * 60 * 1000 - 1000);
    }


}
