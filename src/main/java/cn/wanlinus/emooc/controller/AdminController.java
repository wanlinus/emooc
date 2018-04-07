package cn.wanlinus.emooc.controller;

import cn.wanlinus.emooc.domain.Teacher;
import cn.wanlinus.emooc.domain.User;
import cn.wanlinus.emooc.dto.*;
import cn.wanlinus.emooc.service.TeacherLogService;
import cn.wanlinus.emooc.service.TeacherService;
import cn.wanlinus.emooc.service.UserLogService;
import cn.wanlinus.emooc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanli
 * @date 2018-02-22 16:53
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserLogService userLogService;
    @Autowired
    private TeacherLogService teacherLogService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;

    /**
     * 管理员首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"", "/", "/index", "index.html"})
    public String home(Model model) {
        model.addAttribute("userOperaLogs", userLogService.getTopNumberOrderByTimeDesc(10));
        model.addAttribute("teacherOperaLogs", teacherLogService.getTopNumberOrderByTimeDesc(10));
        return "admin/index";
    }

    /**
     * 性别饼状图
     *
     * @return GenderPieDTO
     */
    @GetMapping("/gender-pie")
    @ResponseBody
    public List<GenderPieDTO> genderPie() {
        return userService.genderPie();
    }

    /**
     * 教师管理模块
     *
     * @return
     */
    @GetMapping("/teacher-manager")
    public String teacherManager() {
        return "admin/teacher-manager";
    }

    @GetMapping("/teacher-add")
    public String teacherAdd() {
        return "admin/teacher-add";
    }

    @GetMapping("user-manager")
    public String userManager() {
        return "admin/user-manager";
    }

    @GetMapping("course-manager")
    public String courseManager() {
        return "admin/course-manager";
    }

    /**
     * 教师信息
     *
     * @return 教师信息列表
     */
    @GetMapping("teacher")
    @ResponseBody
    public LayuiPaginationDataDTO<TeacherDetailsDTO> teacherPage(LayuiPaginationDTO layuiPaginationDTO) {
        Page<Teacher> page = teacherService.pageTeacher(new PageRequest(layuiPaginationDTO.getPage() - 1, layuiPaginationDTO.getLimit()));
        List<TeacherDetailsDTO> list = new ArrayList<>();
        for (Teacher t : page.getContent()) {
            list.add(new TeacherDetailsDTO(t));
        }
        return new LayuiPaginationDataDTO<>(0, "", page.getTotalElements(), list);
    }

    @PostMapping("teacher")
    public String addTeacher(TeacherDetailsDTO dto, RedirectAttributes redirectAttributes) {

        if (teacherService.addTeacher(dto)) {
            redirectAttributes.addFlashAttribute("msg", "添加成功");
        } else {
            redirectAttributes.addFlashAttribute("msg", "添加失败");
        }
        return "admin/teacher-add";
    }

    /**
     * 获取的单个教师信息
     *
     * @param id
     * @return
     */
    @GetMapping("teacher/{id}")
    @ResponseBody
    public Teacher teacherDetail(@PathVariable String id) {
        return null;
    }


    @GetMapping("teacher-info")
    public String teacherInfo() {
        return null;
    }

    //-------------------------用户管理模块-------------------------

    /**
     * 用户分页组件
     *
     * @param layuiPaginationDTO layui分页数据传输对象
     * @return layui分页数据传输对象
     */
    @GetMapping("user")
    @ResponseBody
    public LayuiPaginationDataDTO<UserSimpleDTO> userPage(LayuiPaginationDTO layuiPaginationDTO) {
        Page<User> page = userService.pageUser(new PageRequest(layuiPaginationDTO.getPage() - 1, layuiPaginationDTO.getLimit()));
        List<UserSimpleDTO> list = new ArrayList<>();
        for (User u : page.getContent()) {
            list.add(new UserSimpleDTO(u));
        }
        return new LayuiPaginationDataDTO<>(0, "", page.getTotalElements(), list);
    }

    @GetMapping("user-manager/{id}")
    public String eUser(@PathVariable String id) {
        return "admin/user-details";
    }

    @GetMapping("user/{id}")
    public String userDetails(@PathVariable String id, Model model) {
        User user = userService.userDetails(id);
        return "admin/user-details";
    }
}
