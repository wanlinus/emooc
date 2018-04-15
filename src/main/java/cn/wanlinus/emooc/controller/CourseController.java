package cn.wanlinus.emooc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wanli
 * @date 2018-02-25 16:26
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @GetMapping(value = "")
    public String index() {
        return "course/index";
    }

}
