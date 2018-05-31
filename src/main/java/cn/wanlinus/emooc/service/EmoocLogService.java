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

package cn.wanlinus.emooc.service;

import cn.wanlinus.emooc.domain.EmoocLog;
import cn.wanlinus.emooc.dto.LayuiPaginationDataDTO;
import cn.wanlinus.emooc.dto.LoggerDTO;
import cn.wanlinus.emooc.enums.EmoocRole;

import java.util.Date;
import java.util.List;

/**
 * @author wanli
 * @date 2018-04-16 19:43
 */
public interface EmoocLogService {

    /**
     * 得到用户日志
     *
     * @param startNum 开始条数
     * @param endNum   结束条数
     * @return 用户日志
     */
    List<EmoocLog> getTopUserLog(Integer startNum, Integer endNum);

    /**
     * 获取教师日志
     *
     * @param startNum 开始条数
     * @param endNum   结束条数
     * @return 教师日志
     */
    List<EmoocLog> getTopTeacherLog(Integer startNum, Integer endNum);

    /**
     * 获取用户日志分页信息
     *
     * @param role  查询身份
     * @param page  当前页
     * @param limit 每页条数
     * @return 分页数据
     */
    LayuiPaginationDataDTO<LoggerDTO> pageRoleLogger(EmoocRole role, int page, Integer limit);

    /**
     * 获取指定用户日志分页信息
     *
     * @param roleUser    用户身份
     * @param appointPage 指定页数
     * @param pageSize    没页数据量
     * @param username    用户名
     * @return 分页数据
     */
    List<EmoocLog> pageRoleLogger(EmoocRole roleUser, Integer appointPage, Integer pageSize, String username);

    /**
     * 计数某日课程日志
     *
     * @param time 指定日期
     * @return 统计量
     */
    Long countCourseLogs(Date time);

    /**
     * 计数某日视频日志
     *
     * @param time 指定日期
     * @return 统计量
     */
    Long countVideoLogs(Date time);

    /**
     * 指定日期对教师登录情况进行计数
     *
     * @param date 指定日期
     * @return 统计量
     */
    Long countTeacherLogin(Date date);

    /**
     * 指定日期对教师注册情况进行计数
     *
     * @param date 指定日期
     * @return 统计量
     */
    Long countTeacherRegister(Date date);

    /**
     * 指定日期对用户登陆情况进行计数
     *
     * @param date 指定日期
     * @return 统计量
     */
    Long countUserLogin(Date date);

    /**
     * 指定日期对用户注册情况尽心统计
     *
     * @param date 指定日期
     * @return 统计量
     */
    Long countUserRegister(Date date);

    /**
     * 指定日期对管理员登陆情况进行统计
     *
     * @param date 指定日期
     * @return 统计量
     */
    Long countAdminLogin(Date date);

    /**
     * 指定日期对问题进行统计
     *
     * @param date 指定日期
     * @return 统计量
     */
    Long countQuestions(Date date);

    /**
     * 指定日期对问题的回答进行统计
     *
     * @param date 指定日期
     * @return 统计量
     */
    Long countAnswers(Date date);

    /**
     * 指定日期对课程评论计数
     *
     * @param date 指定日期
     * @return 统计量
     */
    Long countComments(Date date);

    /**
     * 指定日期对课程的笔记进行统计
     *
     * @param date 指定日期
     * @return 统计量
     */
    Long countNotes(Date date);

    /**
     * 指定日期对课程评分进行统计
     *
     * @param date 指定日期
     * @return 统计量
     */
    Long countScores(Date date);

    /**
     * 对指定用户日志进行计数
     *
     * @param userName 用户Id
     * @return 统计量
     */
    Long countUserLogs(String userName);


    /**
     * 保存日志
     *
     * @param log 日志对象
     */
    void save(EmoocLog log);
}
