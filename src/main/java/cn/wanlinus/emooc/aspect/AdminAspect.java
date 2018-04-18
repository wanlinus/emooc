package cn.wanlinus.emooc.aspect;

import cn.wanlinus.emooc.annotation.AdminAnnotation;
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
import java.util.Arrays;
import java.util.Date;

/**
 * @author wanli
 * @date 2018-04-06 21:30
 */
@Aspect
@Component
public class AdminAspect {

    @Autowired
    private EmoocLogRepository emoocLogRepository;

    @Autowired
    private EmoocErrorRepository emoocErrorRepository;

    @Autowired
    private HttpServletRequest request;


    @Pointcut(value = "@annotation(cn.wanlinus.emooc.annotation.AdminAnnotation)")
    public void admin() {
    }

    @Transactional(rollbackFor = Exception.class)
    @Around("admin() && @annotation(adminAnnotation)")
    public Object adminAround(ProceedingJoinPoint joinPoint, AdminAnnotation adminAnnotation) throws Throwable {
        Object obj = joinPoint.proceed();
        EmoocLog log = new EmoocLog();
        log.setId(CommonUtils.adminLogId());
        log.setRole(EmoocRole.ROLE_ADMIN);
        log.setOperation(adminAnnotation.description());
        log.setResult(null);
        log.setIp(request.getRemoteAddr());
        log.setId(CommonUtils.adminLogId());
        log.setTime(new Date());
        log.setEquipment(CommonUtils.getEquipment(request));
        log.setComment(Arrays.toString(joinPoint.getArgs()));
        emoocLogRepository.save(log);
        return obj;
    }

    @Transactional(rollbackFor = Exception.class)
    @AfterThrowing("admin() && @annotation(adminAnnotation)")
    public void afterThrowing(AdminAnnotation adminAnnotation) {
        EmoocError emoocError = new EmoocError();
        emoocError.setId(CommonUtils.errorId());
        emoocError.setTime(new Date());
        emoocError.setWho(AuthUtils.getAuthentication().getName());
        emoocError.setDetails(adminAnnotation.description());
        emoocErrorRepository.save(emoocError);
    }

}
