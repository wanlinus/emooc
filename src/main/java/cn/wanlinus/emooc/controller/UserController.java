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

package cn.wanlinus.emooc.controller;

import cn.wanlinus.emooc.commons.ResultData;
import cn.wanlinus.emooc.dto.*;
import cn.wanlinus.emooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wanli
 * @date 2018-02-22 16:54
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Value("${web.upload-path}")
    private String uploadPath;

    @Value("${web.avatar-path}")
    private String avatarPath;

    @Autowired
    private UserService userService;


    @RequestMapping("index")
    public String index(Model model) {
        model.addAttribute("user", userService.getUser(getUsername()));
        return "user/index";
    }

    @GetMapping("information")
    public String userInformation(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "user/information";
    }

    @GetMapping("oplog")
    public String userOperation(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "user/oplog";
    }

    @GetMapping("setbindsns")
    public String setbindsns(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "user/setbindsns";
    }

    /**
     * 添加课程收藏
     *
     * @param courseId 课程ID
     * @return ResultData
     */
    @GetMapping("rest/collection/course/{courseId}")
    @ResponseBody
    public ResultData<String> collection(@PathVariable String courseId) {
        return userService.collectionCourse(courseId);
    }

    /**
     * 取消课程收藏
     *
     * @param courseId 课程ID
     * @return ResultData
     */
    @GetMapping("rest/collection/cancel/{courseId}")
    @ResponseBody
    public ResultData<String> collectionCancel(@PathVariable String courseId) {
        return userService.collectionCourseCancel(courseId);
    }

    /**
     * 判断课程是否被当前登陆用户收藏
     *
     * @param courseId 课程ID
     * @return ResultData
     */
    @GetMapping("rest/isCollection/{courseId}")
    @ResponseBody
    public ResultData<String> isCollection(@PathVariable String courseId) {
        return userService.isCollectionCourse(courseId);
    }

    /**
     * 更改用户信息
     *
     * @param dto 用户信息传输对象
     * @return ResultData
     */
    @PutMapping("rest/information")
    @ResponseBody
    public ResultData<String> changeInformation(@RequestBody UserInformationDTO dto) {
        return userService.changeInformation(dto);
    }

    /**
     * 修改用邮件
     *
     * @param newEmail 邮件电话数据传输对象
     * @return ResultData
     */
    @PutMapping("rest/email")
    @ResponseBody
    public ResultData<String> changeEmail(@RequestBody UserNewEmailPhoneDTO newEmail) {
        return userService.changeEmail(newEmail.getNewEmail());
    }

    /**
     * 修改用户电话号码
     *
     * @param newPhone 邮件电话数据传输对象
     * @return ResultData
     */
    @PutMapping("rest/phone")
    @ResponseBody
    public ResultData<String> changePhone(@RequestBody UserNewEmailPhoneDTO newPhone) {
        return userService.changePhone(newPhone.getNewPhone());
    }

    /**
     * 修改用户头像
     *
     * @param userAvatar 用户头像数据
     * @return ResultData
     */
    @PutMapping("rest/avatar")
    @ResponseBody
    public ResultData<String> changeUserAvatar(@RequestParam("avatar") MultipartFile userAvatar) {
        return userService.updateAvatar(userAvatar);
    }

    /**
     * 当前登陆用户日志分页数据
     *
     * @param appointPage 指定页数
     * @param pageSize    没页数据
     * @return ResultData
     */
    @GetMapping("rest/log")
    @ResponseBody
    public ResultData<BootstrapPaginationDataDTO<BootstrapPaginationDataLogDTO>>
    userLog(Integer appointPage, @RequestParam(defaultValue = "10") Integer pageSize) {
        return userService.pageLog(appointPage, pageSize);
    }

    /**
     * 修改用户密码
     *
     * @param dto 用户密码数据传输对象
     * @return ResultData
     */
    @PutMapping("rest/password")
    @ResponseBody
    public ResultData<String> changePassword(@RequestBody UserChangePasswordDTO dto) {
        return userService.changePassword(dto);
    }
}
