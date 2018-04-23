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

import cn.wanlinus.emooc.domain.Course;
import cn.wanlinus.emooc.dto.ThAddCourseDTO;
import cn.wanlinus.emooc.service.CourseClassificationService;
import cn.wanlinus.emooc.service.CourseDirectionService;
import cn.wanlinus.emooc.service.CourseTypeService;
import cn.wanlinus.emooc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static cn.wanlinus.emooc.utils.CommonUtils.filename;

/**
 * @author wanli
 * @date 2018-02-22 16:54
 */
@Controller
@RequestMapping("teacher")
public class TeacherController {

    @Resource(name = "path")
    private String path;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseDirectionService directionService;

    @Autowired
    private CourseClassificationService classificationService;

    @Autowired
    private CourseTypeService typeService;

    @GetMapping(value = {"", "/", "index"})
    public String index(Model model) {
        model.addAttribute("tops", teacherService.topCourses());
        return "teacher/index";
    }

    @GetMapping(value = "course/add")
    public String add(Model model) {
        model.addAttribute("directions", directionService.getDirections());
        model.addAttribute("classifications", classificationService.getClassifications(String.valueOf(1)));
        model.addAttribute("types", typeService.getTypes());
        return "teacher/course/add";
    }

    @PostMapping(value = "course/add")
    public String addCourse(@ModelAttribute ThAddCourseDTO dto, @RequestParam("pic") MultipartFile pic, RedirectAttributes redirectAttributes) throws IOException {
        String filename = filename() + pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf("."));
        FileOutputStream fos = new FileOutputStream(new File(path + filename));
        FileInputStream fs = (FileInputStream) pic.getInputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fs.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        fs.close();
        Course course = teacherService.addCourse(dto, filename);
        if (course != null) {
            redirectAttributes.addFlashAttribute("msg", "添加成功");
        } else {
            redirectAttributes.addFlashAttribute("msg", "添加失败");
        }
        return "redirect:/teacher/course/add";
    }
}
