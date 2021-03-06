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

import cn.wanlinus.emooc.domain.Teacher;
import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.dto.*;
import cn.wanlinus.emooc.enums.EmoocRole;
import cn.wanlinus.emooc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wanli
 * @date 2018-02-22 16:53
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private EmoocLogService logService;

    /**
     * 管理员首页
     */
    @RequestMapping(value = {"", "/", "/index", "index.html"})
    public String home(Model model) {
        //用户详情
        model.addAttribute("userNum", userService.countUsers());
        model.addAttribute("userRegister", userService.countUserRegister(new Date()));
        model.addAttribute("todayUserLogin", userService.countUserLogin(new Date()));
        //讲师详情
        model.addAttribute("allTeachers", teacherService.countTeachers());
        model.addAttribute("teacherRegister", teacherService.countTeacherRegister(new Date()));
        model.addAttribute("todayTeachersLogin", teacherService.countTeacherLogin(new Date()));
        //课程详情
        model.addAttribute("allCourses", courseService.countCourses());
        model.addAttribute("todayAddCourses", courseService.currentDayNewlyIncreased());
        model.addAttribute("allVideos", courseService.countCourseSectionVideos());
        model.addAttribute("todayAddVideos", courseService.currentDayVideoNewlyIncreased());
        return "admin/index";
    }

    /**
     * 管理员详情
     */
    @GetMapping(value = "information")
    public String information() {
        return "admin/information";
    }

    /**
     * 更改管理员密码界面
     */
    @GetMapping("change-password")
    public String chgPassword() {
        return "admin/change-password";
    }

    /**
     * 性别饼状图
     *
     * @return GenderPieDTO
     */
    @GetMapping("gender-pie")
    @ResponseBody
    public List<GenderPieDTO> genderPie() {
        return userService.genderPie();
    }

    /**
     * 返回管理员主页用户注册和课程添加数据
     *
     * @return StatisticsDTO
     */
    @GetMapping(value = "statistics")
    @ResponseBody
    public StatisticsDTO statistics() {
        return adminService.statistics();
    }

    /**
     * 课程方向统计
     *
     * @return CourseDirectionPieDTO
     */
    @GetMapping(value = "courseDirectionPie")
    @ResponseBody
    public List<CourseDirectionPieDTO> courseDirectionPie() {
        return adminService.courseDirectionPie();
    }

    /**
     * 课程操作统计
     *
     * @return QuesNoteDTO
     */
    @GetMapping(value = "courseQuesNote")
    @ResponseBody
    public QuesNoteDTO quesNote() {
        return adminService.quesNote();
    }

    /**
     * 讲师管理系统
     */
    @GetMapping("tms")
    public String teacherManagerSystem() {
        return "admin/tms/index";
    }

    /**
     * 教师信息
     *
     * @return 教师信息列表
     */
    @GetMapping("tms/teacher")
    @ResponseBody
    public LayuiPaginationDataDTO<TeacherDetailsDTO> teacherPage(LayuiPaginationDTO dto) {
        return teacherService.pageTeacher(dto);
    }

    /**
     * 添加教师界面
     */
    @GetMapping("tms/add")
    public String addTeacherUI() {
        return "admin/tms/add";
    }

    /**
     * 添加教师
     *
     * @param dto                教师信息
     * @param redirectAttributes redirectAttributes
     * @return view name
     */
    @PostMapping("tms/teacher")
    public String addTeacher(TeacherDetailsDTO dto, RedirectAttributes redirectAttributes) {
        if (teacherService.addTeacher(dto) != null) {
            redirectAttributes.addFlashAttribute("msg", "添加成功");
        } else {
            redirectAttributes.addFlashAttribute("msg", "添加失败");
        }
        return "redirect:add";
    }

    /**
     * 获取的单个教师信息
     *
     * @param id
     * @return
     */
    @GetMapping("tms/teacher/{id}")
    @ResponseBody
    public Teacher teacherDetail(@PathVariable String id) {
        return null;
    }


    //-------------------------用户管理模块-------------------------

    /**
     * 用户管理系统
     *
     * @return
     */
    @GetMapping("sms")
    public String subscriberManagementSystem() {
        return "admin/sms/index";
    }


    /**
     * 用户分页组件
     *
     * @param layuiPaginationDTO layui分页数据传输对象
     * @return layui分页数据传输对象
     */
    @GetMapping("sms/user")
    @ResponseBody
    public LayuiPaginationDataDTO<UserSimpleDTO> userPage(LayuiPaginationDTO layuiPaginationDTO) {
        Page<User> page = userService.pageUser(new PageRequest(layuiPaginationDTO.getPage() - 1,
                layuiPaginationDTO.getLimit()));
        List<UserSimpleDTO> list = new ArrayList<>();
        for (User u : page.getContent()) {
            list.add(new UserSimpleDTO(u));
        }
        return new LayuiPaginationDataDTO<>(0, "", page.getTotalElements(), list);
    }

    @GetMapping("sms/user/{id}")
    public String userDetail(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.userDetails(id));
        return "admin/sms/user";
    }

    //-------------------------课程管理模块-------------------------

    /**
     * 课程管理系统
     *
     * @return
     */
    @GetMapping("cms")
    public String courseManagerSystem() {
        return "admin/cms/index";
    }

    //-------------------------日志管理模块-------------------------

    @GetMapping("lms")
    public String logManagerSystem() {
        return "admin/lms/index";
    }

    @GetMapping("lms/teacher")
    public String lmsTeacher() {
        return "admin/lms/teacher";
    }

    @GetMapping("logger/teacher")
    @ResponseBody
    public LayuiPaginationDataDTO<LoggerDTO> loggerTeacher(LayuiPaginationDTO layuiPaginationDTO) {
        return logService.pageRoleLogger(EmoocRole.ROLE_TEACHER, layuiPaginationDTO.getPage() - 1,
                layuiPaginationDTO.getLimit());
    }

    @GetMapping("lms/user")
    public String lmsUser() {
        return "admin/lms/user";
    }

    @GetMapping("logger/user")
    @ResponseBody
    public LayuiPaginationDataDTO<LoggerDTO> loggerUser(LayuiPaginationDTO layuiPaginationDTO) {
        return logService.pageRoleLogger(EmoocRole.ROLE_USER, layuiPaginationDTO.getPage() - 1,
                layuiPaginationDTO.getLimit());
    }

    @GetMapping("lms/admin")
    public String lmsAdmin() {
        return "admin/lms/admin";
    }

    @GetMapping("logger/admin")
    @ResponseBody
    public LayuiPaginationDataDTO<LoggerDTO> loggerAdmin(LayuiPaginationDTO layuiPaginationDTO) {
        return logService.pageRoleLogger(EmoocRole.ROLE_ADMIN, layuiPaginationDTO.getPage() - 1,
                layuiPaginationDTO.getLimit());
    }

    @GetMapping("lms/error")
    public String lmsError() {
        return "admin/lms/error";
    }

}
