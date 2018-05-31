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
import cn.wanlinus.emooc.dto.ForgetPasswordDTO;
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
import javax.servlet.http.HttpSession;
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
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        super.addViewControllers(registry);
    }

    @GetMapping({"/", "index"})
    public String index() {
        String page = null;
        String role = AuthUtils.getRole();
        if (ROLE_USER.equals(role)) {
            page = "redirect:/course";
        } else if (ROLE_TEACHER.equals(role)) {
            page = "redirect:/teacher/index";
        } else if (ROLE_ADMIN.equals(role)) {
            page = "redirect:/admin/index";
        } else if (role != null) {
            page = "redirect:/login";
        }
        return page;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        if (getAuthentication() != null) {
            new SecurityContextLogoutHandler().logout(request, response, getAuthentication());
        }
        return "redirect:/index";
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

    @GetMapping("/forget/password/email")
    @ResponseBody
    public ResultData<String> forgetPassword(String email, String code, HttpSession session) {
        ResultData<String> resultData = null;
        if (code == null) {
            resultData = userService.forgetPassword(email);
            if (resultData.getData() != null) {
                session.setAttribute("forgetCode", resultData.getData());
                resultData.setData(null);
            }
        } else {
            resultData = new ResultData<>();
            String forgetCode = String.valueOf(session.getAttribute("forgetCode"));
            if (forgetCode != null) {
                if (code.equals(forgetCode)) {
                    resultData.setCode(true);
                } else {
                    resultData.setCode(false);
                    resultData.setMessage("验证码错误");
                }
            } else {
                resultData.setCode(false);
                resultData.setMessage("验证码为空");
            }
        }
        return resultData;
    }

    @PostMapping("/password")
    @ResponseBody
    public ResultData<String> changePassword(@RequestBody ForgetPasswordDTO dto, HttpSession session) {
        ResultData<String> resultData = new ResultData<>();
        String code = String.valueOf(session.getAttribute("forgetCode"));
        if (code.equals(dto.getCode())) {
            resultData = userService.changePassword(dto.getEmail(), dto.getPassword());
        } else {
            resultData.setCode(false);
            resultData.setMessage("验证码错误");
        }
        return resultData;
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
    public ResultData<String> register(@RequestBody @Valid UserRegisterDTO dto, Errors errors) {
        ResultData<String> resultData = new ResultData<>();
        if (errors.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            for (Object o : errors.getAllErrors()) {
                msg.append(o.toString(), o.toString().lastIndexOf("[") + 1, o.toString().lastIndexOf("]"));
            }
            resultData.setCode(false);
            resultData.setMessage(msg.toString());
            return resultData;
        } else {
            return userService.register(dto);
        }

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
