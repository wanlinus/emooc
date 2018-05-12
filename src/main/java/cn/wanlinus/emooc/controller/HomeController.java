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
import cn.wanlinus.emooc.dto.UserRegisterDTO;
import cn.wanlinus.emooc.service.UserService;
import cn.wanlinus.emooc.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static cn.wanlinus.emooc.utils.AuthUtils.*;


/**
 * @author wanli
 * @date 2018-02-22 10:07
 */
@Controller
@RequestMapping("/")
public class HomeController extends WebMvcConfigurerAdapter {


    @Autowired
    private UserService userService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/try").setViewName("try");
        registry.addViewController("/login").setViewName("login");
        super.addViewControllers(registry);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        if (getAuthentication() != null) {
            new SecurityContextLogoutHandler().logout(request, response, getAuthentication());
        }
        return "redirect:/login";
    }

    @GetMapping("/dispatcher")
    public String dispatcher(RedirectAttributes redirect) {
        String page = null;
        String role = AuthUtils.getRole();
        if (ROLE_USER.equals(role)) {
            page = "redirect:/course";
        } else if (ROLE_TEACHER.equals(role)) {
            page = "redirect:/teacher/index";
        } else if (ROLE_ADMIN.equals(role)) {
            page = "redirect:/admin/index";
        } else if (role != null) {
            redirect.addFlashAttribute("msg", "登陆过期,请重新登陆");
            page = "redirect:/index";
        }
        return page;
    }


    @GetMapping("/register")
    public String registerUI() {
        return "register";
    }

    /**
     * 检查用户名是否存在
     *
     * @param username 需要检查的用户名
     * @return 查找到返回true否则返回false
     */
    @GetMapping("check/name")
    @ResponseBody
    public ResultData<String> checkName(String username) {
        return userService.checkName(username);
    }

    /**
     * @param email 需要查找的邮箱
     * @return 查找到返回true否则返回false
     */
    @GetMapping("check/email")
    @ResponseBody
    public ResultData<String> checkEmail(String email) {
        return userService.checkEmail(email);

    }

    @GetMapping("check/all")
    @ResponseBody
    public ResultData<String> checkUsernameEmail(String username, String email) {
        return userService.checkAll(username, email);
    }

    @PostMapping("register")
    @ResponseBody
    public ResultData<Boolean> register(@RequestBody @Valid UserRegisterDTO dto, Errors errors) {
        ResultData<Boolean> resultData = new ResultData<>();
        if (errors.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            for (Object o : errors.getAllErrors()) {
                msg.append(o.toString().substring(o.toString().lastIndexOf("[") + 1, o.toString().lastIndexOf("]")));
            }
            resultData.setCode(false);
            resultData.setMessage(msg.toString());
        } else {
            resultData.setCode(userService.register(dto) != null);
        }
        return resultData;
    }

    /**
     * 用户激活
     *
     * @param userId  用户ID
     * @param captcha 激活码Id
     * @return ResultData
     */
    @GetMapping("active/user/{userId}/{captcha}")
    @ResponseBody
    public ResultData<String> activeUser(@PathVariable String userId, @PathVariable String captcha) {
        return userService.active(userId, captcha);
    }
}
