package cn.wanlinus.emooc.annotation;

import java.lang.annotation.*;

/**
 * 用户记录用户操作的注解
 *
 * @author wanli
 * @date 2018-03-30 20:59
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserAnnotation {
    String value() default "";

    /**
     * 用于描述相应操作
     *
     * @return 操作说明
     */
    String description();

}
