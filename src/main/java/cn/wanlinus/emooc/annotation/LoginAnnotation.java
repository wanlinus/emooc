package cn.wanlinus.emooc.annotation;

import java.lang.annotation.*;

/**
 * @author wanli
 * @date 2018-04-16 14:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginAnnotation {
    String value() default "";

    /**
     * 描述 必填项
     *
     * @return 这个有什么返回啊
     */
    String description();
}
