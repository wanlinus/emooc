/*
 * Copyright (C) 2018. - wanli <wanlinus@qq.com>
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

package cn.wanlinus.emooc;

import java.util.Date;

/**
 * @author wanli
 * @date 2018-04-08 13:49
 */
public class TimeTest {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date(1523232000000L));
        System.out.println(new Date(1523200000000L));
    }
}
