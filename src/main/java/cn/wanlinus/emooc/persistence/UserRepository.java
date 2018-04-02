package cn.wanlinus.emooc.persistence;

import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.persistence.custom.UserCustomPersistence;

/**
 * @author wanli
 * @date 2018-02-22 11:17
 */
public interface UserRepository extends BaseRepository<User, String>, UserCustomPersistence {
    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 返回查找到的用户
     */
    User findByUsername(String username);

    /**
     * 通过邮箱查找用户
     *
     * @param email 需要查找的邮箱
     * @return 返回查找到的用户
     */
    User findByEmail(String email);
}
