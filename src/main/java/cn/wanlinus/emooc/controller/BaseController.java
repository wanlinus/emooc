package cn.wanlinus.emooc.controller;

import cn.wanlinus.emooc.utils.AuthUtils;

/**
 * @author wanli
 * @date 2018-04-15 01:42
 */
public abstract class BaseController {

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public String getUsername() {
        return AuthUtils.getUsername();
    }

}
