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
public @interface UserRegisterLogger {
    String value() default "";

    /**
     * 并没有什么用,知识写来耍的
     *
     * @return 用于描述东西的
     */
    String descript() default "";
}
