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

import cn.wanlinus.emooc.persistence.EmoocLogRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wanli
 * @date 2018-03-30 20:58
 */
@Aspect
@Component
public class LoggerAspect {

    private static Logger logger = LoggerFactory.getLogger(LoggerAspect.class);


    @Autowired
    private HttpServletRequest request;
    @Autowired
    private EmoocLogRepository emoocLogRepository;

    /**
     * 用户注册切点
     */
    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.RegisterAnnotation)")
    private void userRegister() {
    }

    /**
     * 代表以UserOperationLogger注解了的切点
     */
    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.UserAnnotation)")
    public void user() {
    }

    /**
     * 这个切点肯定就是表明老师的骚操作了三
     */
    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.TeacherAnnotation)")
    public void teacher() {
    }

    /**
     * 管理员的上帝视角
     * 史上最骚的操作
     */
    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.AdminAnnotation)")
    public void admin() {
    }


    /**
     * 代理注解的方法
     *
     * @param joinPoint 连接点
     * @return 被注解方法的返回值
     * @throws Throwable 抛出的异常啊,,,为什么这个东西还要让注释
     */
    @Around("user()")
    public Object userAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(String.valueOf(joinPoint.getArgs()));

        //Todo

        Object obj = joinPoint.proceed();
        return obj;
    }

    @Around("teacher()")
    public Object teacherAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(String.valueOf(joinPoint.getArgs()));
        for (Object o : joinPoint.getArgs()) {
            logger.info(o.toString());
        }

        Object obj = joinPoint.proceed();
        return obj;
    }

}
