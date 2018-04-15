package cn.wanlinus.emooc.controller;

import cn.wanlinus.emooc.commons.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wanli
 * @date 2018-02-22 16:54
 */
@Controller
@RequestMapping("teacher")
public class TeacherController {

    @GetMapping(value = {"", "/", "index"})
    public String index() {
        return "teacher/index";
    }

    @GetMapping(value = "add-course")
    public String addCourseUI() {
        return "teacher/add-course";
    }

    @PostMapping(value = "add-course")
    @ResponseBody
    public ResultData<String> addCourse() {
        ResultData<String> resultData = new ResultData<>();
        return resultData;
    }
}
