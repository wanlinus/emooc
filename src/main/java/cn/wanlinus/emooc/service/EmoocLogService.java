package cn.wanlinus.emooc.service;

import cn.wanlinus.emooc.domain.EmoocLog;

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
}
