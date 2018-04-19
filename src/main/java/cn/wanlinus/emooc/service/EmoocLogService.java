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

import cn.wanlinus.emooc.domain.EmoocLog;
import cn.wanlinus.emooc.dto.LayuiPaginationDataDTO;
import cn.wanlinus.emooc.dto.LoggerDTO;
import cn.wanlinus.emooc.enums.EmoocRole;

import java.util.List;

/**
 * @author wanli
 * @date 2018-04-16 19:43
 */
public interface EmoocLogService {

    /**
     * 得到用户日志
     *
     * @param startNum 开始条数
     * @param endNum   结束条数
     * @return 用户日志
     */
    List<EmoocLog> getTopUserLog(Integer startNum, Integer endNum);

    /**
     * 获取教师日志
     *
     * @param startNum 开始条数
     * @param endNum   结束条数
     * @return 教师日志
     */
    List<EmoocLog> getTopTeacherLog(Integer startNum, Integer endNum);

    /**
     * 获取用户日志分页信息
     *
     * @param role  查询身份
     * @param page  当前页
     * @param limit 每页条数
     * @return 分页数据
     */
    LayuiPaginationDataDTO<LoggerDTO> pageRoleLogger(EmoocRole role, int page, Integer limit);

}
