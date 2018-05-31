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
import cn.wanlinus.emooc.domain.EmoocLog;
import cn.wanlinus.emooc.dto.BootstrapPaginationDataDTO;
import cn.wanlinus.emooc.dto.BootstrapPaginationDataLogDTO;
import cn.wanlinus.emooc.dto.UserChangePasswordDTO;
import cn.wanlinus.emooc.dto.UserInformationDTO;
import cn.wanlinus.emooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping("rest/collection/course/{courseId}")
    @ResponseBody
    public ResultData<String> collection(@PathVariable String courseId) {
        return userService.collectionCourse(courseId);
    }

    @GetMapping("rest/collection/cancel/{courseId}")
    @ResponseBody
    public ResultData<String> collectionCancel(@PathVariable String courseId) {
        return userService.collectionCourseCancel(courseId);
    }

    @GetMapping("rest/isCollection/{courseId}")
    @ResponseBody
    public ResultData<String> isCollection(@PathVariable String courseId) {
        return userService.isCollectionCourse(courseId);
    }

    @PutMapping("rest/information")
    @ResponseBody
    public ResultData<String> changeInformation(@RequestBody UserInformationDTO dto) {
        return userService.changeInformation(dto);
    }

    @PutMapping("rest/avatar")
    @ResponseBody
    public ResultData<String> changeUserAvatar(@RequestParam("avatar") MultipartFile userAvatar) {
        return userService.updateAvatar(userAvatar);
    }

    @GetMapping("rest/log")
    @ResponseBody
    public ResultData<BootstrapPaginationDataDTO<BootstrapPaginationDataLogDTO>>
    userLog(Integer appointPage, @RequestParam(defaultValue = "10") Integer pageSize) {
        return userService.pageLog(appointPage, pageSize);
    }

    @PutMapping("rest/password")
    @ResponseBody
    public ResultData<String> changePassword(@RequestBody UserChangePasswordDTO dto) {
        return userService.changePassword(dto);
    }
}
