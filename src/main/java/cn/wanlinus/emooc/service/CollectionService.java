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

/**
 * @author wanli
 * @date 2018/5/11
 */
public interface CollectionService {
    /**
     * 添加收藏
     *
     * @param uid      用户ID
     * @param courseId 课程ID
     * @return 受影响行数
     */
    Integer addCollection(String uid, String courseId);

    /**
     * 删除收藏课程
     *
     * @param uid      用户Id
     * @param courseId 课程ID
     * @return 受影响的行数
     */
    Integer removeCollection(String uid, String courseId);
}
