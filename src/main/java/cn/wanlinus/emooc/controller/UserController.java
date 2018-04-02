package cn.wanlinus.emooc.controller;

import cn.wanlinus.emooc.commons.ResultData;
import cn.wanlinus.emooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wanli
 * @date 2018-02-22 16:54
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


}
