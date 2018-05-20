/*
 * Copyright (C) 2018 - wanli <wanlinus@qq.com>
 *
 * This file is part of emooc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.wanlinus.emooc.aspect;

import cn.wanlinus.emooc.annotation.LoginAnnotation;
import cn.wanlinus.emooc.domain.EmoocError;
import cn.wanlinus.emooc.domain.EmoocLog;
import cn.wanlinus.emooc.enums.EmoocRole;
import cn.wanlinus.emooc.persistence.EmoocErrorRepository;
import cn.wanlinus.emooc.persistence.EmoocLogRepository;
import cn.wanlinus.emooc.utils.AuthUtils;
import cn.wanlinus.emooc.utils.CommonUtils;
import com.alibaba.fastjson.JSON;
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
public class LoginAspect {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private EmoocLogRepository emoocLogRepository;
    @Autowired
    private EmoocErrorRepository emoocErrorRepository;


    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.LoginAnnotation)")
    public void login() {
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
        log.setType(loginAnnotation.type());
        log.setComment(JSON.toJSONString(object) + JSON.toJSONString(joinPoint.getArgs()));
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
        if (object != null) {
            log.setResult(true);
        } else {
            log.setResult(false);
        }
        emoocLogRepository.save(log);
        return object;
    }

    @AfterThrowing("login()")
    public void throwErr() {
        EmoocError emoocError = new EmoocError();
        emoocError.setId(CommonUtils.errorId());
        emoocError.setTime(new Date());
        emoocError.setWho(AuthUtils.getAuthentication().getName());
        emoocError.setDetails("登陆操作错误");
        emoocErrorRepository.save(emoocError);
    }
}
