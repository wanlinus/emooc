package cn.wanlinus.emooc.aspect;

import cn.wanlinus.emooc.annotation.LoginAnnotation;
import cn.wanlinus.emooc.annotation.LogoutAnnotation;
import cn.wanlinus.emooc.domain.EmoocError;
import cn.wanlinus.emooc.domain.EmoocLog;
import cn.wanlinus.emooc.enums.EmoocRole;
import cn.wanlinus.emooc.persistence.EmoocErrorRepository;
import cn.wanlinus.emooc.persistence.EmoocLogRepository;
import cn.wanlinus.emooc.utils.AuthUtils;
import cn.wanlinus.emooc.utils.CommonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * @author wanli
 * @date 2018-04-16 14:34
 */
@Aspect
@Component
public class LogInOutAspect {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private EmoocLogRepository emoocLogRepository;
    @Autowired
    private EmoocErrorRepository emoocErrorRepository;


    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.LoginAnnotation)")
    public void login() {
    }

    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.LogoutAnnotation)")
    public void logout() {
    }

    @Transactional(rollbackFor = Exception.class)
    @Around("login() && @annotation(loginAnnotation)")
    public Object loginAround(ProceedingJoinPoint joinPoint, LoginAnnotation loginAnnotation) throws Throwable {
        String username = joinPoint.getArgs()[0].toString();
        EmoocLog log = new EmoocLog();
        Object object = joinPoint.proceed();
        log.setWho(username);
        log.setTime(new Date());
        log.setIp(request.getRemoteAddr());
        log.setEquipment(CommonUtils.getEquipment(request));
        log.setOperation(loginAnnotation.description());
        log = setRole(log);
        emoocLogRepository.save(log);
        return object;
    }

    @Transactional(rollbackFor = Exception.class)
    @Around("logout() && @annotation(logoutAnnotation)")
    public Object logoutAround(ProceedingJoinPoint joinPoint, LogoutAnnotation logoutAnnotation) throws Throwable {
        EmoocLog log = new EmoocLog();
        log.setWho(AuthUtils.getUsername());
        log.setTime(new Date());
        log.setIp(request.getRemoteAddr());
        log.setEquipment(CommonUtils.getEquipment(request));
        log.setOperation(logoutAnnotation.description());
        log = setRole(log);
        emoocLogRepository.save(log);
        return joinPoint.proceed();
    }

    private EmoocLog setRole(EmoocLog log) {
        String role = Objects.requireNonNull(CommonUtils.getRequest()).getParameter("role");
        if (EmoocRole.ROLE_USER.getDesc().equals(role)) {
            log.setId(CommonUtils.userLogId());
            log.setRole(EmoocRole.ROLE_USER);
        } else if (EmoocRole.ROLE_TEACHER.getDesc().equals(role)) {
            log.setId(CommonUtils.teacherLogId());
            log.setRole(EmoocRole.ROLE_TEACHER);
        } else if (EmoocRole.ROLE_ADMIN.getDesc().equals(role)) {
            log.setId(CommonUtils.adminLogId());
            log.setRole(EmoocRole.ROLE_ADMIN);
        } else {
            log.setId(CommonUtils.errorId());
            log.setRole(EmoocRole.ROLE_UNKNOWN);
        }
        return log;
    }


    @AfterThrowing("login() && logout()")
    public void throwErr() {
        EmoocError emoocError = new EmoocError();
        emoocError.setId(CommonUtils.errorId());
        emoocError.setTime(new Date());
        emoocError.setWho(AuthUtils.getAuthentication().getName());
        emoocError.setDetails("登陆操作错误");
        emoocErrorRepository.save(emoocError);
    }
}
