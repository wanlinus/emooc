package cn.wanlinus.emooc.service;

import cn.wanlinus.emooc.domain.EmoocLog;
import cn.wanlinus.emooc.dto.LayuiPaginationDataDTO;
import cn.wanlinus.emooc.dto.LoggerDTO;
import cn.wanlinus.emooc.enums.EmoocRole;

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

}
