package cn.wanlinus.emooc.controller;

import cn.wanlinus.emooc.domain.Teacher;
import cn.wanlinus.emooc.dto.GenderPieDTO;
import cn.wanlinus.emooc.dto.LayuiPagination;
import cn.wanlinus.emooc.dto.LayuiPaginationDataDTO;
import cn.wanlinus.emooc.dto.TeacherDetailsDTO;
import cn.wanlinus.emooc.service.TeacherOperationLogService;
import cn.wanlinus.emooc.service.TeacherService;
import cn.wanlinus.emooc.service.UserOperationLogService;
import cn.wanlinus.emooc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class AdminController extends WebMvcConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserOperationLogService userOperationLogService;
    @Autowired
    private TeacherOperationLogService teacherOperationLogService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("admin/teacher-manager").setViewName("/admin/teacher-manager");
        registry.addViewController("admin/user-manager").setViewName("/admin/user-manager");
        registry.addViewController("admin/course-manager").setViewName("/admin/course-manager");
    }

    /**
     * 管理员首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"", "/", "/index", "index.html"})
    public String home(Model model) {
        model.addAttribute("userOperaLogs", userOperationLogService.getTopNumberOrderByTimeDesc(10));
        model.addAttribute("teacherOperaLogs", teacherOperationLogService.getTopNumberOrderByTimeDesc(10));
        return "admin/index";
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

    /**
     * 教师信息
     *
     * @return 教师信息列表
     */
    @GetMapping("teacher")
    @ResponseBody
    public LayuiPaginationDataDTO<TeacherDetailsDTO> teacherPage(LayuiPagination layuiPagination) {
        Page<Teacher> page = teacherService.pageTeacher(new PageRequest(layuiPagination.getPage() - 1, layuiPagination.getLimit()));
        List<TeacherDetailsDTO> list = new ArrayList<>();
        for (Teacher t : page.getContent()) {
            list.add(new TeacherDetailsDTO(t));
        }
        return new LayuiPaginationDataDTO<>(0, "", page.getSize(), list);
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

    @GetMapping("teacher-info")
    public String teacherInfo() {
        return null;
    }

}
