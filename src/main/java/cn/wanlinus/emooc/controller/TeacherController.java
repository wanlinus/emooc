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
import cn.wanlinus.emooc.dto.ThAddCourseDTO;
import cn.wanlinus.emooc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;

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


    @GetMapping(value = {"", "/", "index"})
    public String index() {
        return "teacher/index";
    }

    @GetMapping("course/add")
    public String add() {
        return "teacher/course/add";
    }

    @PostMapping(value = "add-course")
    @ResponseBody
    public ResultData<String> addCourse(ThAddCourseDTO dto, @RequestParam("pic") MultipartFile multipartFile) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(path + multipartFile.getOriginalFilename()));
        FileInputStream fs = (FileInputStream) multipartFile.getInputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fs.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        fs.close();
        teacherService.addCourse(dto, multipartFile.getOriginalFilename());

        ResultData<String> resultData = new ResultData<>();
        return resultData;
    }
}
