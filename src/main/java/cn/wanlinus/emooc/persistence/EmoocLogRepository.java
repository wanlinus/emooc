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

package cn.wanlinus.emooc.persistence;

import cn.wanlinus.emooc.domain.EmoocLog;
import cn.wanlinus.emooc.enums.EmoocRole;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @author wanli
 * @date 2018-04-16 17:05
 */
public interface EmoocLogRepository extends BaseRepository<EmoocLog, String> {

    /**
     * 获取用户分页日志
     *
     * @param startNum 开始条数
     * @param endNum   结束条数
     * @return 用户日志列表
     */
    @Query(value = "SELECT * FROM TB_LOG AS log WHERE log.LOG_ROLE=0 ORDER BY log.LOG_TIME DESC LIMIT ?1, ?2", nativeQuery = true)
    List<EmoocLog> getTopUserLog(Integer startNum, Integer endNum);

    /**
     * 获取教师前15条日志
     *
     * @param startNum 开始条数
     * @param endNum   结束条数
     * @return 教师日志
     */
    @Query(value = "SELECT * FROM TB_LOG AS log WHERE log.LOG_ROLE=1 ORDER BY log.LOG_TIME DESC LIMIT ?1, ?2", nativeQuery = true)
    List<EmoocLog> getTopTeacherLog(Integer startNum, Integer endNum);

    /**
     * 获取管理员前15条日志
     *
     * @param startNum 开始条数
     * @param endNum   结束条数
     * @return 管理员日志
     */
    @Query(value = "SELECT * FROM TB_LOG AS log WHERE log.LOG_ROLE=2 ORDER BY log.LOG_TIME DESC LIMIT ?1, ?2;", nativeQuery = true)
    List<EmoocLog> getTopAdminLog(Integer startNum, Integer endNum);

    /**
     * 根据用户身份得到日志记录总数
     *
     * @param role 身份
     * @return 教师日志条数
     */
    @Query(value = "SELECT COUNT(log) FROM EmoocLog AS log WHERE log.role=?1")
    Long countLog(EmoocRole role);


    /**
     * 根据用户身份不同获取不同日志信息
     *
     * @param role       身份
     * @param startIndex 开始索引
     * @param size       数据量
     * @return 日志数据
     */
    @Query(value = "SELECT * FROM TB_LOG AS log WHERE log.LOG_ROLE=?1 ORDER BY log.LOG_TIME DESC LIMIT ?2 , ?3", nativeQuery = true)
    List<EmoocLog> pageRoleLogger(Integer role, int startIndex, Integer size);

    /**
     * 获取指定身份指定用户名的日志信息
     *
     * @param roleUser    用户身份
     * @param appointPage 指定索引
     * @param pageSize    没页数据量
     * @param username    用户名
     * @return 日志数据
     */
    @Query(value = "SELECT * FROM TB_LOG AS log WHERE log.LOG_ROLE=?1 AND log.LOG_WHO=?4 ORDER BY LOG_TIME DESC LIMIT ?2,?3", nativeQuery = true)
    List<EmoocLog> pageRoleLogger(Integer roleUser, Integer appointPage, Integer pageSize, String username);

    /**
     * 查询某一时间段不同身份操作类型
     *
     * @param role 身份
     * @param type 操作类型
     * @param date 日期
     * @return 操作量
     */
    @Query(value = "SELECT COUNT(*) FROM TB_LOG AS log WHERE log.LOG_ROLE = ?1 AND log.LOG_TYPE = ?2 AND DATE_FORMAT(log.LOG_TIME, '%Y-%m-%d') = DATE_FORMAT(?3 , '%Y-%m-%d') AND log.LOG_RESULT=1", nativeQuery = true)
    Long countRoleType(Integer role, Integer type, Date date);


    /**
     * 查询在某一时间段所有操作类型
     *
     * @param type 操作类型
     * @param date 日期
     * @return 操作量
     */
    @Query(value = "SELECT COUNT(*) FROM TB_LOG AS log WHERE log.LOG_TYPE = ?1 AND DATE_FORMAT(log.LOG_TIME, '%Y-%m-%d') = DATE_FORMAT(?2 , '%Y-%m-%d') AND log.LOG_RESULT=1", nativeQuery = true)
    Long countLogType(Integer type, Date date);

    /**
     * 获取传入日期添加课程数
     *
     * @param time 指定日期
     * @return 添加课程总数
     */
    @Query(value = "SELECT COUNT(*) FROM TB_LOG AS log WHERE log.LOG_TYPE = 3 AND log.LOG_RESULT = 1 AND DATE_FORMAT(log.LOG_TIME, '%Y-%m-%d') = DATE_FORMAT( ?1 , '%Y-%m-%d')", nativeQuery = true)
    Long countCourses(Date time);

    /**
     * 获取传入日期添加视频数
     *
     * @param time 指定日期
     * @return 添加视频总数
     */
    @Query(value = "SELECT COUNT(*) FROM TB_LOG AS log WHERE log.LOG_TYPE = 5 AND log.LOG_RESULT = 1 AND DATE_FORMAT(log.LOG_TIME, '%Y-%m-%d') = DATE_FORMAT( ?1 , '%Y-%m-%d')", nativeQuery = true)
    Long countVideos(Date time);

    /**
     * 对用户日志进行计数
     *
     * @param userName 用户名
     * @return 统计量
     */
    @Query(value = "SELECT count(*) FROM TB_LOG AS log WHERE log.LOG_WHO = ?1 AND log.LOG_ROLE = 0", nativeQuery = true)
    Long countUserLogs(String userName);


}
