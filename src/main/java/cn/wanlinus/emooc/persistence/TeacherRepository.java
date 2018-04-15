package cn.wanlinus.emooc.persistence;

import cn.wanlinus.emooc.domain.Teacher;

/**
 * @author wanli
 * @date 2018-03-07 09:54
 */
public interface TeacherRepository extends BaseRepository<Teacher, String> {

    /**
     * 通过用户名查找教师
     *
     * @param username 教师用户名
     * @return 查找到返回相应教师否咋返回空
     */
    Teacher findByUsername(String username);
}
