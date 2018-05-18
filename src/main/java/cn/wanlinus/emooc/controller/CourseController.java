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
import cn.wanlinus.emooc.domain.CourseClassification;
import cn.wanlinus.emooc.domain.CourseDirection;
import cn.wanlinus.emooc.domain.CourseType;
import cn.wanlinus.emooc.dto.CourseScoreDTO;
import cn.wanlinus.emooc.dto.NoteDTO;
import cn.wanlinus.emooc.dto.QuesNoteScoreReturnDTO;
import cn.wanlinus.emooc.dto.QuestionDTO;
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
    @GetMapping(value = {"", "index", "/"})
    public String index(Model model) {
        model.addAttribute("directions", courseService.getCourseDirections());
        model.addAttribute("classifications", courseService.getClassifications());
        model.addAttribute("courses", courseService.getAllCoursesDescDate());
        return "course/index";
    }

    /**
     * 跳转到课程方向页面
     *
     * @param directionId      课程方向ID
     * @param classificationId 课程类型
     * @param pageSize         没页页数
     * @param page             第几页
     * @param model            model
     * @return 指定页面
     */
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

    /**
     * 跳转到指定课程详情页面
     *
     * @param courseId 课程ID
     * @param model    model
     * @return 页面名称
     */
    @GetMapping("learn/{courseId}")
    public String courseLearn(@PathVariable("courseId") String courseId, Model model) {
        model.addAttribute("course", courseService.getCourse(courseId));
        model.addAttribute("recommends", courseService.recommendCourse());
        return "course/learn";
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

    /**
     * 获取所有课程方向
     *
     * @return 课程方向列表
     */
    @GetMapping("rest/direction")
    @ResponseBody
    public List<CourseDirection> directions() {
        return directionService.getDirections();
    }

    /**
     * 获取指定课程方向
     *
     * @param directionId 课程方向ID
     * @return 指定课程方向对象
     */
    @GetMapping("rest/direction/{directionId}")
    @ResponseBody
    public CourseDirection getCourseDirection(@PathVariable("directionId") String directionId) {
        return directionService.getDirection(directionId);
    }

    /**
     * 获取所有课程类型
     *
     * @return 课程类型列表
     */
    @GetMapping("rest/classifications")
    @ResponseBody
    public List<CourseClassification> classifications() {
        return classificationService.getClassifications();
    }

    /**
     * 课程提问
     *
     * @param dto 提问数据传输对象
     * @return resultData
     */
    @PostMapping("rest/question")
    @ResponseBody
    public ResultData<QuesNoteScoreReturnDTO> addQuestion(@RequestBody QuestionDTO dto) {
        return courseService.addQuestion(dto.getCourseId(), dto.getQuestion());
    }

    /**
     * 为课程添加笔记
     *
     * @param dto 笔记数据传输对象
     * @return resultData
     */
    @PostMapping("rest/note")
    @ResponseBody
    public ResultData<QuesNoteScoreReturnDTO> addNote(@RequestBody NoteDTO dto) {
        return courseService.addNote(dto);
    }

    /**
     * 为课程添加评分
     *
     * @param dto 评分数据传输对象
     * @return resultData
     */
    @PostMapping("rest/score")
    @ResponseBody
    public ResultData<QuesNoteScoreReturnDTO> addScore(@RequestBody CourseScoreDTO dto) {
        return courseService.addScore(dto);
    }

}
