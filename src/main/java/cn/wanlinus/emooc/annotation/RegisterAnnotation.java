package cn.wanlinus.emooc.annotation;

import java.lang.annotation.*;

/**
 * 用户注册时打的注解
 *
 * @author wanli
 * @date 2018-04-01 00:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RegisterAnnotation {
    String value() default "";

    /**
     * 用于描述相应操作
     *
     * @return 操作说明
     */
    String description();
}
