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

import cn.wanlinus.emooc.domain.Admin;
import cn.wanlinus.emooc.persistence.custom.AdminCustomPersistence;

/**
 * @author wanli
 * @date 2018-02-22 13:08
 */
public interface AdminRepository extends BaseRepository<Admin, String>, AdminCustomPersistence {
    /**
     * 通过名字查找Admin
     *
     * @param name 用户名
     * @return 查找到的管理员
     * @date 2018-3-25
     */
    Admin findByName(String name);

}
