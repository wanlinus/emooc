package cn.wanlinus.emooc.controller;

import cn.wanlinus.emooc.commons.ResultData;
import cn.wanlinus.emooc.exception.ResultDataException;
import cn.wanlinus.emooc.exception.SimpleException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author wanli
 * @date 2018-04-05 16:28
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    @Value("message.exception")
    String errorMessage;

    @ExceptionHandler(ResultDataException.class)
    public ResultData<Object> exceptionHandler() {
        ResultData<Object> resultData = new ResultData<>();
        resultData.setCode(false);
        resultData.setMessage(errorMessage);
        return resultData;
    }

    @ExceptionHandler(SimpleException.class)
    public String simpleException(Model model) {
        model.addAttribute("error", "服务器错误");
        return "error";
    }
}
