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

package cn.wanlinus.emooc.service;

import cn.wanlinus.emooc.domain.CourseDirection;

import java.util.List;

/**
 * @author wanli
 * @date 2018-04-22 21:07
 */
public interface CourseDirectionService {
    /**
     * 获取所有课程方向
     *
     * @return 课程方向
     */
    List<CourseDirection> getDirections();

    /**
     * 返回指定课程方向
     *
     * @param directionId 指定课程ID
     * @return 课程方向
     */
    CourseDirection getDirection(String directionId);
}
