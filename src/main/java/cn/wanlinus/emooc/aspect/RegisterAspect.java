package cn.wanlinus.emooc.aspect;

import cn.wanlinus.emooc.annotation.UserRegister;
import cn.wanlinus.emooc.domain.EmoocError;
import cn.wanlinus.emooc.domain.EmoocLog;
import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.dto.UserRegisterDTO;
import cn.wanlinus.emooc.enums.EmoocRole;
import cn.wanlinus.emooc.persistence.EmoocErrorRepository;
import cn.wanlinus.emooc.persistence.EmoocLogRepository;
import cn.wanlinus.emooc.utils.CommonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * @author wanli
 * @date 2018-04-01 00:45
 */
@Aspect
@Component
public class RegisterAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private EmoocLogRepository logRepository;

    @Autowired
    private EmoocErrorRepository errorRepository;


    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.UserRegister)")
    public void register() {
    }

    @Around("register() && @annotation(userRegister)")
    public Object registerAround(ProceedingJoinPoint joinPoint, UserRegister userRegister) throws Throwable {
        Object object = joinPoint.proceed();
        EmoocLog log = new EmoocLog();
        log.setId(CommonUtils.userLogId());
        log.setRole(EmoocRole.ROLE_USER);
        log.setTime(new Date());
        log.setIp(request.getRemoteAddr());
        log.setEquipment(CommonUtils.getEquipment(request));
        log.setOperation(userRegister.descript());
        if (object instanceof User) {
            log.setWho(((User) object).getUsername());
            log.setResult(true);
            log.setComment(object.toString());
        } else {
            log.setWho(((UserRegisterDTO) joinPoint.getArgs()[0]).getUsername());
            log.setResult(false);
            log.setComment(Arrays.toString(joinPoint.getArgs()));
        }
        logRepository.save(log);
        return object;
    }

    @AfterThrowing("register()")
    public void error() {

    }


}
