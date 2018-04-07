package cn.wanlinus.emooc.annotation;

import java.lang.annotation.*;

/**
 * 注解什么的都是浮云
 *
 * @author wanli
 * @date 2018-03-30 21:02
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TeacherOperation {
    String value() default "";

    /**
     * 用于描述相应操作
     *
     * @return 操作说明
     */
    String descript();
}
