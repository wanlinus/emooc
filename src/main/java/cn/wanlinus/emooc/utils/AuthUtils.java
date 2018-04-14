package cn.wanlinus.emooc.utils;

import org.hibernate.AssertionFailure;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author wanli
 * @date 2018-04-06 00:17
 */
public final class AuthUtils {
    public AuthUtils() {
        throw new AssertionError();
    }

    /**
     * 得到认证对象
     *
     * @return Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取登录用户名
     *
     * @return 用户名
     */
    public static String getUsername() {
        return getAuthentication().getName();
    }

    /**
     * 获取身份信息
     *
     * @return role
     */
    public static String getRole() {
        return getAuthentication().getAuthorities().toString();
    }
}
