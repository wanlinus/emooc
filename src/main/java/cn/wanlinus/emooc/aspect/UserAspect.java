package cn.wanlinus.emooc.aspect;

import cn.wanlinus.emooc.annotation.UserOperation;
import cn.wanlinus.emooc.domain.EmoocLog;
import cn.wanlinus.emooc.enums.EmoocRole;
import cn.wanlinus.emooc.persistence.EmoocLogRepository;
import cn.wanlinus.emooc.utils.AuthUtils;
import cn.wanlinus.emooc.utils.CommonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author wanli
 * @date 2018-04-06 22:46
 */
@Aspect
@Component
public class UserAspect {


    @Autowired
    private EmoocLogRepository logRepository;

    @Autowired
    private HttpServletRequest request;

    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.UserOperation)")
    private void user() {
    }

    @Transactional(rollbackFor = Exception.class)
    @Around("user() && @annotation(userOperation)")
    public Object around(ProceedingJoinPoint joinPoint, UserOperation userOperation) throws Throwable {
        EmoocLog log = new EmoocLog();
        log.setId(CommonUtils.userLogId());
        log.setWho(AuthUtils.getUsername());
        log.setRole(EmoocRole.ROLE_USER);
        log.setOperation(userOperation.descript());
        log.setEquipment(CommonUtils.getEquipment(request));
        log.setIp(request.getRemoteAddr());
        log.setTime(new Date());
        logRepository.save(log);
        return joinPoint.proceed();

    }
}
