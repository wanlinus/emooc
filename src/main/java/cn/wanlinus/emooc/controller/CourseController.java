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

import cn.wanlinus.emooc.domain.CourseClassification;
import cn.wanlinus.emooc.domain.CourseDirection;
import cn.wanlinus.emooc.domain.CourseType;
import cn.wanlinus.emooc.service.CourseClassificationService;
import cn.wanlinus.emooc.service.CourseDirectionService;
import cn.wanlinus.emooc.service.CourseService;
import cn.wanlinus.emooc.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wanli
 * @date 2018-02-25 16:26
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTypeService typeService;

    @Autowired
    private CourseDirectionService directionService;

    @Autowired
    private CourseClassificationService classificationService;

    /**
     * 首页
     *
     * @param model Spring Model
     * @return 课程主页面
     */
    @GetMapping(value = {"", "index"})
    public String index(Model model) {
        model.addAttribute("directions", courseService.getCourseDirections());
        model.addAttribute("classifications", courseService.getClassifications());
        model.addAttribute("courses", courseService.getAllCoursesDescDate());
        return "course/index";
    }

    /**
     * 返回rest课程类型数据
     *
     * @return CourseType
     */
    @GetMapping("rest/types")
    @ResponseBody
    public List<CourseType> types() {
        return typeService.getTypes();
    }

    @GetMapping("rest/direction")
    @ResponseBody
    public List<CourseDirection> directions() {
        return directionService.getDirections();
    }

    @GetMapping("direction")
    public String direction(String directionId, String classificationId,
                            @RequestParam(name = "pageSize", defaultValue = "15") Integer pageSize,
                            @RequestParam(name = "page", defaultValue = "0") Integer page,
                            Model model) {
        if (directionId != null) {
            model.addAttribute("directions", courseService.getCourseDirectionDTOs(directionId));
            model.addAttribute("classifications", courseService.getClassificationDTOListByDirection(directionId));
        } else {
            model.addAttribute("directions", courseService.getDirectionByClassification(classificationId));
            model.addAttribute("classifications", courseService.getClassificationDTOList(classificationId));
        }
        model.addAttribute("courses", courseService.pageCourse(pageSize, page, directionId, classificationId));
        return "course/direction/index";
    }

    @GetMapping("rest/direction/{directionId}")
    @ResponseBody
    public CourseDirection getCourseDirection(@PathVariable("directionId") String directionId) {
        return directionService.getDirection(directionId);
    }

    @GetMapping("rest/classifications")
    @ResponseBody
    public List<CourseClassification> classifications() {
        return classificationService.getClassifications();
    }
}
