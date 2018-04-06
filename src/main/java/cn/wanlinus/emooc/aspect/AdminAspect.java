package cn.wanlinus.emooc.aspect;

import cn.wanlinus.emooc.annotation.AdminOperation;
import cn.wanlinus.emooc.domain.Admin;
import cn.wanlinus.emooc.domain.AdminLog;
import cn.wanlinus.emooc.domain.EmoocError;
import cn.wanlinus.emooc.persistence.AdminLogRepository;
import cn.wanlinus.emooc.persistence.AdminRepository;
import cn.wanlinus.emooc.persistence.ErrorRepository;
import cn.wanlinus.emooc.utils.AuthUtils;
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
 * @date 2018-04-06 21:30
 */
@Aspect
@Component
public class AdminAspect {

    @Autowired
    private AdminLogRepository adminLogRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ErrorRepository errorRepository;

    @Autowired
    private HttpServletRequest request;


    @Pointcut(value = "@annotation(cn.wanlinus.emooc.annotation.AdminOperation)")
    public void admin() {
    }

    @Around("admin() && @annotation(adminOperation)")
    public Object adminAround(ProceedingJoinPoint joinPoint, AdminOperation adminOperation) throws Throwable {
        AdminLog log = new AdminLog();
        Admin admin = adminRepository.findByName(AuthUtils.getAuthentication().getName());
        log.setAdmin(admin);
        log.setDate(new Date());
        log.setDetail(adminOperation.descript() + Arrays.toString(joinPoint.getArgs()));
        String eq = request.getHeader("User-Agent");
        log.setEquipment(eq.substring(eq.indexOf("(") + 1, eq.indexOf(")")));
        log.setIp(request.getRemoteAddr());
        log.setId(CommonUtils.adminLogId());
        Object obj = joinPoint.proceed();
        if (obj instanceof Boolean) {
            if ((Boolean) obj) {
                log.setResult(true);
            } else {
                log.setResult(false);
            }
        } else {
            log.setComment("发生未知错误");
        }
        adminLogRepository.save(log);
        return obj;
    }

    @AfterThrowing("admin()")
    private void afterThrowing() {
        EmoocError emoocError = new EmoocError();
        emoocError.setId(CommonUtils.errorId());
        emoocError.setTime(new Date());
        emoocError.setWho(AuthUtils.getAuthentication().getName());
        emoocError.setDetails("管理员操作异常");
        errorRepository.save(emoocError);
    }

}
