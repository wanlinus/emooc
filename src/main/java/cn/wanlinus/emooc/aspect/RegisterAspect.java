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

import cn.wanlinus.emooc.annotation.RegisterAnnotation;
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


    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.RegisterAnnotation)")
    public void register() {
    }

    @Around("register() && @annotation(registerAnnotation)")
    public Object registerAround(ProceedingJoinPoint joinPoint, RegisterAnnotation registerAnnotation) throws Throwable {
        Object object = joinPoint.proceed();
        EmoocLog log = new EmoocLog();
        log.setId(CommonUtils.userLogId());
        log.setRole(EmoocRole.ROLE_USER);
        log.setTime(new Date());
        log.setIp(request.getRemoteAddr());
        log.setEquipment(CommonUtils.getEquipment(request));
        log.setType(registerAnnotation.type());
        log.setComment(object.toString() + Arrays.toString(joinPoint.getArgs()));
        logRepository.save(log);
        return object;
    }

    @AfterThrowing("register()")
    public void error() {

    }


}
