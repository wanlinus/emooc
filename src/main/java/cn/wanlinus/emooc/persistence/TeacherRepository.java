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

package cn.wanlinus.emooc.persistence;

import cn.wanlinus.emooc.domain.Teacher;

/**
 * @author wanli
 * @date 2018-03-07 09:54
 */
public interface TeacherRepository extends BaseRepository<Teacher, String> {

    /**
     * 通过用户名查找教师
     *
     * @param username 教师用户名
     * @return 查找到返回相应教师否咋返回空
     */
    Teacher findByUsername(String username);
}
