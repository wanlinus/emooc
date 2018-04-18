package cn.wanlinus.emooc.persistence;

import cn.wanlinus.emooc.domain.EmoocLog;
import org.springframework.data.jpa.repository.Query;

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
    @Query(value = "SELECT * FROM tb_log AS log WHERE log.LOG_ROLE=0 ORDER BY log.LOG_TIME DESC LIMIT ?1, ?2", nativeQuery = true)
    List<EmoocLog> getTopUserLog(Integer startNum, Integer endNum);

    /**
     * 获取教师前15条日志
     *
     * @param startNum 开始条数
     * @param endNum   结束条数
     * @return 教师日志
     */
    @Query(value = "SELECT * FROM tb_log AS log WHERE log.LOG_ROLE=1 ORDER BY log.LOG_TIME DESC LIMIT ?1, ?2", nativeQuery = true)
    List<EmoocLog> getTopTeacherLog(Integer startNum, Integer endNum);

    /**
     * 获取管理员前15条日志
     *
     * @param startNum 开始条数
     * @param endNum   结束条数
     * @return 管理员日志
     */
    @Query(value = "SELECT * FROM tb_log AS log WHERE log.LOG_ROLE=2 ORDER BY log.LOG_TIME DESC LIMIT ?1, ?2;", nativeQuery = true)
    List<EmoocLog> getTopAdminLog(Integer startNum, Integer endNum);

    /**
     * 对教师记录计数
     *
     * @return 教师日志条数
     */
    @Query(value = "select count(log) from EmoocLog as log where log.role=1")
    Long countTeacherLog();

    /**
     * 教师日志分页查询
     *
     * @param startIndex 开始索引
     * @param size       条数
     * @return 教师日志分页数据
     */
    @Query(value = "SELECT * FROM tb_log AS log WHERE log.LOG_ROLE=1 LIMIT ?1 , ?2", nativeQuery = true)
    List<EmoocLog> pageTeacherLogger(int startIndex, int size);
}
