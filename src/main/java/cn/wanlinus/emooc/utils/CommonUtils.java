package cn.wanlinus.emooc.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wanli
 * @date 2018-02-22 11:43
 */
public final class CommonUtils {

    private CommonUtils() {
        throw new AssertionError();
    }

    /**
     * 描述：获取Request
     *
     * @return request对象，可能为null
     * @author wanli
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != requestAttributes) {
            return requestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 生成UID的方法
     *
     * @return uid
     */
    public static String uid() {
        return "U" + System.currentTimeMillis();
    }

    /**
     * 用户操作生成主键
     *
     * @return user operation log id
     */
    public static String userLogId() {
        return "LU" + System.currentTimeMillis();
    }


    /**
     * md5摘要
     *
     * @param password 需要加密的原文
     * @return 返回md5摘要
     */
    public static String md5Encrypt(String password) {
        return DigestUtils.md5Hex(password);
    }

}
