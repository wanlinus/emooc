package cn.wanlinus.emooc.persistence;

import cn.wanlinus.emooc.domain.Admin;
import cn.wanlinus.emooc.persistence.custom.AdminCustomPersistence;

/**
 * @author wanli
 * @date 2018-02-22 13:08
 */
public interface AdminCustomRepository extends BaseRepository<Admin, String>, AdminCustomPersistence {
    /**
     * 通过名字查找Admin
     *
     * @param name 用户名
     * @return 查找到的管理员
     * @date 2018-3-25
     */
    Admin findByName(String name);

}
