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
public @interface TeacherOperationLogger {
    String value() default "";
}
