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

package cn.wanlinus.emooc.service.impl;

import cn.wanlinus.emooc.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author wanli
 * @date 2018-05-12 19:53
 */
@Service
public class CommonsServiceImpl implements CommonService {

    @Autowired
    private SimpleMailMessage mailMessage;

    @Autowired
    private JavaMailSender javaMailSender;



    /**
     * 异步发送邮件
     *
     * @param emailAddress 邮箱地址
     * @param msg          信息
     */
    @Async
    @Override
    public void asyncSendMail(String emailAddress, String msg) {
        mailMessage.setSubject("用户注册");
        mailMessage.setTo(emailAddress);
        mailMessage.setText(msg);
        javaMailSender.send(mailMessage);
    }
}
