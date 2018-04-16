package cn.wanlinus.emooc.controller;

import cn.wanlinus.emooc.commons.ResultData;
import cn.wanlinus.emooc.dto.UserRegisterDTO;
import cn.wanlinus.emooc.service.UserService;
import cn.wanlinus.emooc.utils.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static cn.wanlinus.emooc.utils.AuthUtils.*;


/**
 * @author wanli
 * @date 2018-02-22 10:07
 */
@Controller
public class HomeController extends WebMvcConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/try").setViewName("try");
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dispatcher")
    public String dispatcher(RedirectAttributes redirect) {
        String page = null;
        String role = AuthUtils.getRole();
        if (ROLE_USER.equals(role)) {
            page = "redirect:/user/index";
        } else if (ROLE_TEACHER.equals(role)) {
            page = "redirect:/teacher/index";
        } else if (ROLE_ADMIN.equals(role)) {
            page = "redirect:/admin/index";
        } else if (role != null) {
            redirect.addFlashAttribute("msg", "登陆过期,请重新登陆");
            page = "redirect:/index";
        }
        return page;
    }


    @GetMapping("/register")
    public String registerUI() {
        return "register";
    }

    /**
     * 检查用户名是否存在
     *
     * @param username 需要检查的用户名
     * @return 查找到返回true否则返回false
     */
    @GetMapping("check/name/{username}")
    @ResponseBody
    public ResultData<Boolean> checkName(@PathVariable("username") String username) {
        System.out.println(username);
        return new ResultData<>(userService.checkName(username));
    }

    /**
     * @param email 需要查找的邮箱
     * @return 查找到返回true否则返回false
     */
    @GetMapping("check/email/{email}")
    @ResponseBody
    public ResultData<Boolean> checkEmail(@PathVariable("email") String email) {
        return new ResultData<>(userService.checkEmail(email));
    }


    @PostMapping("/register")
    @ResponseBody
    public ResultData<Boolean> register(@RequestBody @Valid UserRegisterDTO dto, Errors errors) {
        ResultData<Boolean> resultData = new ResultData<>();
        if (errors.hasErrors()) {
            for (Object o : errors.getAllErrors()) {
                System.out.println(o.toString());
            }
            resultData.setCode(false);
            resultData.setMessage("数据校验错误");
        } else {
            resultData.setCode(userService.register(dto) != null);
        }
        logger.info(resultData.getCode().toString());
        return resultData;
    }
}
