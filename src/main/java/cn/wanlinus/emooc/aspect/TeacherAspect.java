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

import cn.wanlinus.emooc.annotation.TeacherAnnotation;
import cn.wanlinus.emooc.domain.EmoocLog;
import cn.wanlinus.emooc.enums.EmoocRole;
import cn.wanlinus.emooc.persistence.EmoocErrorRepository;
import cn.wanlinus.emooc.persistence.EmoocLogRepository;
import cn.wanlinus.emooc.utils.AuthUtils;
import cn.wanlinus.emooc.utils.CommonUtils;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author wanli
 * @date 2018-04-23 01:25
 */
@Aspect
@Component
public class TeacherAspect {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private EmoocLogRepository logRepository;
    @Autowired
    private EmoocErrorRepository errorRepository;

    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.TeacherAnnotation)")
    private void teacher() {
    }

    @Around(value = "teacher() && @annotation(teacherAnnotation)")
    public Object teacherAround(ProceedingJoinPoint joinPoint, TeacherAnnotation teacherAnnotation) throws Throwable {
        Object obj = joinPoint.proceed();
        EmoocLog log = new EmoocLog();
        log.setId(CommonUtils.userLogId());
        log.setWho(AuthUtils.getUsername());
        log.setRole(EmoocRole.ROLE_TEACHER);
        log.setType(teacherAnnotation.type());
        log.setEquipment(CommonUtils.getEquipment(request));
        log.setIp(request.getRemoteAddr());
        log.setTime(new Date());
        log.setResult(true);
        log.setComment(JSON.toJSONString(obj) + JSON.toJSONString(joinPoint.getArgs()));
        logRepository.save(log);
        return obj;
    }
}
