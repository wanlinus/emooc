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
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping(value = "")
    public String index(Model model) {
        List<CourseDirection> list = courseService.getAllCourseDirection();
        model.addAttribute("direction", JSON.toJSONString(list));
        return "course/index";
    }

    @GetMapping("type")
    @ResponseBody
    public List<CourseType> types() {
        return typeService.getTypes();
    }

    @GetMapping("direction")
    @ResponseBody
    public List<CourseDirection> directions() {
        return directionService.getDirections();
    }

    @GetMapping("classification")
    @ResponseBody
    public List<CourseClassification> classifications() {
        return classificationService.getClassifications();
    }
}
