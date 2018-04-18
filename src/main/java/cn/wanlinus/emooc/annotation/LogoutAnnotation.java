package cn.wanlinus.emooc.annotation;

import java.lang.annotation.*;

/**
 * @author wanli
 * @date 2018-04-19 01:15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogoutAnnotation {
    String description();
}
