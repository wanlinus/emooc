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

import cn.wanlinus.emooc.commons.ResultData;
import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.dto.GenderPieDTO;
import cn.wanlinus.emooc.dto.UserDetailsDTO;
import cn.wanlinus.emooc.dto.UserRegisterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * @author wanli
 * @date 2018-03-06 16:07
 */
public interface UserService {
    /**
     * 返回男女用户人数
     *
     * @return 男女比例
     */
    List<GenderPieDTO> genderPie();

    /**
     * 检查用户名是否存在
     *
     * @param username 需要检查的用户名
     * @return resultData
     */
    ResultData<String> checkName(String username);

    /**
     * 检查邮箱是否存在
     *
     * @param email 需要检查的邮箱
     * @return resultData
     */
    ResultData<String> checkEmail(String email);


    /**
     * 同时检查用户名和邮箱是否存在
     *
     * @param username 用户名
     * @param email    邮箱
     * @return resultData
     */
    ResultData<String> checkAll(String username, String email);

    /**
     * 用户注册
     *
     * @param dto 用户注册数据传输对象
     * @return 注册成功返回true否则返回false
     */
    User register(UserRegisterDTO dto);


    /**
     * 用户分页
     *
     * @param pageable 分页信息
     * @return 分页数据
     */
    Page<User> pageUser(Pageable pageable);

    /**
     * 用户详情
     *
     * @param id 用户ID
     * @return ID相应用户
     */
    UserDetailsDTO userDetails(String id);

    /**
     * 对所有用户计数
     *
     * @return 总用户数
     */
    Long countUsers();

    /**
     * 获取用户对象
     *
     * @param username 用户名
     * @return 存在返回用户对象 不存在返回null
     */
    User getUser(String username);

    /**
     * 计算今日用户访问量
     *
     * @param day 日期
     * @return 访问量
     */
    Long countUserLogin(Date day);

    /**
     * 统计传入日期前days天用户登陆量
     *
     * @param date 指定日期
     * @param days 天数
     * @return 统计人数数组
     */
    List<Long> userLoginStatistics(Date date, Integer days);


    /**
     * 计算今日用户注册量
     *
     * @param date 日期
     * @return 注册量
     */
    Long countUserRegister(Date date);

    /**
     * 统计传入日期用户注册量
     *
     * @param date 相应日期
     * @param days 前多少天
     * @return 统计人数数组
     */
    List<Long> userRegisterStatistics(Date date, Integer days);


    /**
     * 用户激活
     *
     * @param userId  需要激活的用户
     * @param captcha 激活码
     * @return 返回对象
     */
    ResultData<String> active(String userId, String captcha);


}
