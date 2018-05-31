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
import cn.wanlinus.emooc.domain.EmoocLog;
import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

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
     * @return resultData
     */
    ResultData<String> register(UserRegisterDTO dto);


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
     * 获取当前用户
     *
     * @return 当前用户
     */
    User getCurrentUser();

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


    /**
     * 收藏课程
     *
     * @param courseId 课程ID
     * @return resultData
     */
    ResultData<String> collectionCourse(String courseId);

    /**
     * 取消收藏
     *
     * @param courseId 课程ID
     * @return 取消收藏课程
     */
    ResultData<String> collectionCourseCancel(String courseId);

    /**
     * 判断当前用户是否收藏该课程
     *
     * @param courseId 课程ID
     * @return 是收藏返回true否则返回false
     */
    ResultData<String> isCollectionCourse(String courseId);

    /**
     * 修改用户信息
     *
     * @param dto 用户信息传输对象
     * @return resultData
     */
    ResultData<String> changeInformation(UserInformationDTO dto);

    /**
     * 更新用户头像
     *
     * @param userAvatar 用户头像
     * @return resultData
     */
    ResultData<String> updateAvatar(MultipartFile userAvatar);

    /**
     * 用户自管理界面日志分页数据
     *
     * @param appointPage 指定页数
     * @param pageSize    没页数据量
     * @return 分页数据
     */
    ResultData<BootstrapPaginationDataDTO<BootstrapPaginationDataLogDTO>> pageLog(Integer appointPage, Integer pageSize);

    /**
     * 忘记密码发送找回密码验证码
     *
     * @param email 指定邮箱
     * @return resultData
     */
    ResultData<String> forgetPassword(String email);

    /**
     * 修改密码
     *
     * @param email    邮箱
     * @param password 要修改的密码
     * @return resultData
     */
    ResultData<String> changePassword(String email, String password);

    /**
     * 修改密码
     *
     * @param dto 修改密码数据传输对象
     * @return resultData
     */
    ResultData<String> changePassword(UserChangePasswordDTO dto);
}
