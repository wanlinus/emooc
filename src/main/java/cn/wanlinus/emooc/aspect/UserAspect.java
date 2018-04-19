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

import cn.wanlinus.emooc.annotation.UserAnnotation;
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

    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.UserAnnotation)")
    private void user() {
    }

    @Transactional(rollbackFor = Exception.class)
    @Around("user() && @annotation(userAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint, UserAnnotation userAnnotation) throws Throwable {
        EmoocLog log = new EmoocLog();
        log.setId(CommonUtils.userLogId());
        log.setWho(AuthUtils.getUsername());
        log.setRole(EmoocRole.ROLE_USER);
        log.setOperation(userAnnotation.description());
        log.setEquipment(CommonUtils.getEquipment(request));
        log.setIp(request.getRemoteAddr());
        log.setTime(new Date());
        logRepository.save(log);
        return joinPoint.proceed();

    }
}
