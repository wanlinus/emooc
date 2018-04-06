package cn.wanlinus.emooc.aspect;

import cn.wanlinus.emooc.annotation.UserOperation;
import cn.wanlinus.emooc.annotation.UserRegister;
import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.domain.UserLog;
import cn.wanlinus.emooc.persistence.UserLogRepository;
import cn.wanlinus.emooc.persistence.UserRepository;
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
    private UserLogRepository userLogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;

    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.UserOperation)")
    private void user() {
    }

    @Transactional(rollbackFor = Exception.class)
    @Around("user() && @annotation(userOperation)")
    public Object around(ProceedingJoinPoint joinPoint, UserOperation userOperation) throws Throwable {
        UserLog log = new UserLog();
        log.setId(CommonUtils.userLogId());
        log.setDetail(userOperation.descript());
        log.setEquipment(CommonUtils.getEquipment(request));
        log.setIp(request.getRemoteAddr());
        log.setTime(new Date());
        User user = userRepository.findByUsername(AuthUtils.getAuthentication().getName());
        log.setUser(user);
        userLogRepository.save(log);
        return joinPoint.proceed();

    }
}
