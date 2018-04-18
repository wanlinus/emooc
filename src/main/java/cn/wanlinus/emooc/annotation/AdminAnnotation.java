package cn.wanlinus.emooc.annotation;

import java.lang.annotation.*;

/**
 * @author wanli
 * @date 2018-04-06 21:23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminAnnotation {
    String value() default "";

    /**
     * 描述管理员的行为
     *
     * @return
     */
    String description() default "";
}
