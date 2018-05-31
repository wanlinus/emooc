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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import java.util.Date;

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

    @Autowired
    private Message message;

    @Async
    @Override
    public void simpleSendMail(String subject, String emailAddress, String msg) {
        mailMessage.setSubject(subject);
        mailMessage.setTo(emailAddress);
        mailMessage.setText(msg);
        javaMailSender.send(mailMessage);
    }

    @Async
    @Override
    public void sslSendMail(String subject, String sendMsg, String sendTos) throws MessagingException {
        //标题
        message.setSubject(subject);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendTos));
        //内容
        message.setText(sendMsg);
        message.setSentDate(new Date());
        Transport.send(message);
    }
}
