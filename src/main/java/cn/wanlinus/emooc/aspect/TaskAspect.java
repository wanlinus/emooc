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

import cn.wanlinus.emooc.annotation.TaskAnnotation;
import cn.wanlinus.emooc.persistence.EmoocTaskRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wanli
 * @date 2018-05-18 22:54
 */
@Aspect
@Component
public class TaskAspect {

    @Autowired
    private EmoocTaskRepository taskRepository;

    @Pointcut("@annotation(cn.wanlinus.emooc.annotation.TaskAnnotation)")
    public void pointCut() {
    }

    @Around(value = "pointCut() && @annotation(taskAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint, TaskAnnotation taskAnnotation) throws Throwable {
        return joinPoint.proceed();
    }
}
