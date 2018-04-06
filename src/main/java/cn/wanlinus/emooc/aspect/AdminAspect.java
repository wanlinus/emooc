package cn.wanlinus.emooc.aspect;

import cn.wanlinus.emooc.annotation.AdminOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author wanli
 * @date 2018-04-06 21:30
 */
@Aspect
@Component
public class AdminAspect {


    @Pointcut(value = "@annotation(cn.wanlinus.emooc.annotation.AdminOperation)")
    public void admin() {
    }

    @Around("admin() && @annotation(adminOperation)")
    public Object adminAround(ProceedingJoinPoint joinPoint, AdminOperation adminOperation) throws Throwable {
        adminOperation.descript();

        return null;
    }
}
