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

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static cn.wanlinus.emooc.utils.CommonUtils.saveFile;

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
        model.addAttribute("classifications", directionService.getDirection(String.valueOf(1)).getClassifications());
        model.addAttribute("types", typeService.getTypes());
        return "teacher/course/add";
    }

    @PostMapping(value = "course/add")
    public ResultData<String> addCourse(@ModelAttribute ThAddCourseDTO dto, @RequestParam("pic") MultipartFile pic) throws IOException {
        dto.setPath(saveFile(pic, uploadPath, imgPath));
        return teacherService.addCourse(dto);
    }

    @GetMapping("course/details/{id}")
    public String courseDerails(@PathVariable("id") String courseId, Model model) {
        model.addAttribute("sections", teacherService.getSectionsDisplay(courseId));
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
    public ResultData<String> addSection(SectionAddDTO dto) {
        return teacherService.addSection(dto);

    }

    @GetMapping("course/section/video/{videoId}")
    public String play(@PathVariable() String videoId, Model model) {
        model.addAttribute("videoPath", teacherService.getCourseVideo(videoId).getPath());
        return "teacher/course/video";
    }

    @PostMapping("course/section/video")
    @ResponseBody
    public ResultData<String> addVideo(CourseSectionVideoAddDTO dto) {
        return teacherService.addSectionVideo(dto);
    }
}

