package cn.wanlinus.emooc.aspect;

import cn.wanlinus.emooc.domain.UserLog;
import cn.wanlinus.emooc.persistence.TeacherOperationLogRepository;
import cn.wanlinus.emooc.persistence.UserOperationLogRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static cn.wanlinus.emooc.utils.CommonUtils.userLogId;

/**
 * @author wanli
 * @date 2018-03-30 20:58
 */
@Aspect
@Component
public class LoggerAspect {

    private static Logger logger = LoggerFactory.getLogger(LoggerAspect.class);


    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserOperationLogRepository userOperationLogRepository;
    @Autowired
    private TeacherOperationLogRepository teacherOperationLogRepository;

    /**
     * 用户注册切点
     */
    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.UserRegister)")
    private void userRegister() {
    }

    /**
     * 代表以UserOperationLogger注解了的切点
     */
    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.UserOperation)")
    public void user() {
    }

    /**
     * 这个切点肯定就是表明老师的骚操作了三
     */
    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.TeacherOperation)")
    public void teacher() {
    }

    /**
     * 管理员的上帝视角
     * 史上最骚的操作
     */
    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.AdminOperation)")
    public void admin() {
    }


    /**
     * 代理注解的方法
     *
     * @param joinPoint 连接点
     * @return 被注解方法的返回值
     * @throws Throwable 抛出的异常啊,,,为什么这个东西还要让注释
     */
    @Around("user()")
    public Object userAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(String.valueOf(joinPoint.getArgs()));

        UserLog log = new UserLog();
        log.setId(userLogId());
        //Todo

        Object obj = joinPoint.proceed();
        return obj;
    }

    @Around("teacher()")
    public Object teacherAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(String.valueOf(joinPoint.getArgs()));
        for (Object o : joinPoint.getArgs()) {
            logger.info(o.toString());
        }

        Object obj = joinPoint.proceed();
        return obj;
    }

    @Around("admin()")
    public Object adminAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = joinPoint.proceed();

        return obj;
    }

}
