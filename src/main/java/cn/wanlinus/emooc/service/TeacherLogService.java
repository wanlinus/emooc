package cn.wanlinus.emooc.service;

import cn.wanlinus.emooc.domain.TeacherLog;
import org.springframework.data.domain.Page;

/**
 * @author wanli
 * @date 2018-03-07 10:39
 */
public interface TeacherLogService {
    /**
     * 得到以Time倒序排序的前number个用户操作记录
     *
     * @param number 指定个数
     * @return 返回分页列表, 由于人笨不好写, 就返回这个类型了哈
     */
    Page<TeacherLog> getTopNumberOrderByTimeDesc(Integer number);
}
