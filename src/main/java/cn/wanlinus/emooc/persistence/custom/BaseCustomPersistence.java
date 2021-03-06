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

package cn.wanlinus.emooc.persistence.custom;

import java.util.List;
import java.util.Map;

/**
 * @author wanli
 * @date 2018-03-25 01:04
 */
public interface BaseCustomPersistence {
    /**
     * 执行SQL语句返回List Map对象
     *
     * @param nativeSql 原生SQL语句
     * @return list Map
     */
    List<Map<String, Object>> nativeQuery(String nativeSql);

    /**
     * 泛型查询 返回泛型对象
     *
     * @param nativeSql 原生SQL语句
     * @param <T>       对象类型
     * @return 返回对象
     */
    <T> T genericsQuery(String nativeSql);


}
