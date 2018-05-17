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

import java.util.Date;
import java.util.List;

/**
 * @author wanli
 * @date 2018-05-17 20:26
 */
public interface AnswerService {

    /**
     * 统计传入日期教师回答量
     *
     * @param date 相应日期
     * @param days 前多少天
     * @return 统计量
     */
    List<Long> answerStatistics(Date date, int days);

    /**
     * 对传入当日回答尽心统计
     *
     * @param date 传入日期
     * @return 统计量
     */
    Long countAnswers(Date date);
}
