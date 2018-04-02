package cn.wanlinus.emooc.enums;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用于描述用户状态的枚举类型
 *
 * @author wanli
 * @date 2018-02-22 10:48
 */
public enum UserStatus {
    INACTIVE("用户未激活"),
    ACTIVATED("已激活"),
    DELETED("用户已删除"),
    DISABLED("用户禁用"),
    UNKNOWN("未知状态");

    private String name;

    UserStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
