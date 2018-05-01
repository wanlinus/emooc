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
import cn.wanlinus.emooc.dto.*;
import cn.wanlinus.emooc.service.CourseClassificationService;
import cn.wanlinus.emooc.service.CourseDirectionService;
import cn.wanlinus.emooc.service.CourseTypeService;
import cn.wanlinus.emooc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

import static cn.wanlinus.emooc.utils.CommonUtils.preFilename;

/**
 * @author wanli
 * @date 2018-02-22 16:54
 */
@Controller
@RequestMapping("teacher")
public class TeacherController {

    @Value("${web.upload-path}")
    private String uploadPath;

    @Value("${web.image-path}")
    private String imgPath;

    @Value("${web.video-path}")
    private String videoPath;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseDirectionService directionService;

    @Autowired
    private CourseClassificationService classificationService;

    @Autowired
    private CourseTypeService typeService;

    @GetMapping(value = {"", "/", "index"})
    public String index(Model model, HttpSession session) {
        TeacherDetailsDTO detailsDTO = teacherService.getInfo();
        session.setAttribute("avatar", detailsDTO.getAvatar());
        model.addAttribute("tops", teacherService.topCourses());
        model.addAttribute("teacherInfo", detailsDTO);
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
        String filename = preFilename() + pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf("."));
        File file = new File(uploadPath + imgPath + filename);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file);
        FileInputStream fs = (FileInputStream) pic.getInputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fs.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        fs.close();
        Course course = teacherService.addCourse(dto, imgPath + filename);
        if (course != null) {
            redirectAttributes.addFlashAttribute("msg", "添加成功");
        } else {
            redirectAttributes.addFlashAttribute("msg", "添加失败");
        }
        return "redirect:/teacher/course/add";
    }

    @GetMapping("course/details/{id}")
    public String courseDerails(@PathVariable("id") String courseId, Model model) {
        model.addAttribute("sections", teacherService.getSections(courseId));
        model.addAttribute("courseDetails", teacherService.getCourseDetails(courseId));
        return "teacher/course/details";
    }

    @GetMapping("course/page")
    @ResponseBody
    public List<ThCourseDTO> courseList(Pageable pageable) {
        return teacherService.pageCourse(pageable);
    }

    @GetMapping("course/section/{courseId}")
    public String addSectionUI(@PathVariable String courseId, Model model) {
        model.addAttribute("course", teacherService.getCourse(courseId));
        System.err.println(courseId);
        return "teacher/course/section";
    }

    @PostMapping("course/section")
    @ResponseBody
    public String addSection(SectionAddDTO dto) {
        if (teacherService.addSection(dto) != null) {
            return "成功";
        } else {
            return "失败";
        }
    }

    @PostMapping("course/section/video")
    @ResponseBody
    public String addVideo(CourseSectionVideoAddDTO dto) throws IOException {
        String filename = videoPath + preFilename() + dto.getVideo().getOriginalFilename().substring(dto.getVideo().getOriginalFilename().lastIndexOf("."));
        File file = new File(uploadPath + filename);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file);
        FileInputStream fs = (FileInputStream) dto.getVideo().getInputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fs.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        fs.close();
        dto.setSha1(String.valueOf(file.hashCode()));
        dto.setVideoPath(filename);
        return teacherService.addSectionVideo(dto) != null ? "true" : "false";
    }
}

