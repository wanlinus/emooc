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
public @interface UserOperationLogger {
    String value() default "";

    /**
     * 并没有什么用,知识写来耍的
     *
     * @return 用于描述东西的
     */
    String descript() default "";

}
