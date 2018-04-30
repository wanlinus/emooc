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

import cn.wanlinus.emooc.dto.StatisticsDTO;

import java.util.Date;
import java.util.List;

/**
 * @author wanli
 * @date 2018-04-16 19:43
 */
public interface AdminService {
    /**
     * 管理页面
     * 用户注册, 讲师注册,添加课程,添加视频统计
     *
     * @return StatisticsDTO
     */
    StatisticsDTO statistics();

    /**
     * 计数某日登陆量
     *
     * @param date 指定天数
     * @return 登陆量
     */
    Long countAdminLogin(Date date);

    /**
     * 指定date前days登陆统计量
     *
     * @param date 指定日期
     * @param days 天数
     * @return 统计量
     */
    List<Long> adminLoginStatistics(Date date, Integer days);
}
