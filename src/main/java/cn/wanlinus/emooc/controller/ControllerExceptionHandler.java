/*
 * Copyright (C) 2018. - wanli <wanlinus@qq.com>
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
