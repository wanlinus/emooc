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

package cn.wanlinus.emooc.persistence;

import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.enums.UserStatus;
import cn.wanlinus.emooc.persistence.custom.UserCustomPersistence;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

/**
 * @author wanli
 * @date 2018-02-22 11:17
 */
public interface UserRepository extends BaseRepository<User, String>, UserCustomPersistence {
    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 返回查找到的用户
     */
    User findByUsername(String username);

    //    @Query("select user from User as user where user.username=?1 and user.userStatus=?2")
    User findByUsernameAndUserStatus(String username, UserStatus userStatus);

    /**
     * 通过邮箱查找用户
     *
     * @param email 需要查找的邮箱
     * @return 返回查找到的用户
     */
    User findByEmail(String email);

    /**
     * 用户性别饼状图
     *
     * @return pie
     * @date 2018-3-25 02:19:58
     */
    @Query(value = "SELECT user.gender AS gender,COUNT(user.gender) AS number FROM User AS user GROUP BY user.gender")
    Map<String, Integer> genderPie();
}
