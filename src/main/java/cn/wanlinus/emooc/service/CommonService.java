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

package cn.wanlinus.emooc.service;

import javax.mail.MessagingException;

/**
 * @author wanli
 * @date 2018-05-12 18:29
 */
public interface CommonService {
    /**
     * 异步发送邮件
     *
     * @param emailAddress 邮箱地址
     * @param msg          发送信息
     * @param subject      主题
     */
    void simpleSendMail(String subject, String emailAddress, String msg);

    /**
     * 异步发送ssl邮件
     *
     * @param subject 发送主题
     * @param sendMsg 发送信息
     * @param sendTos 收件地址
     * @throws MessagingException 恶心的抛异常
     */
    void sslSendMail(String subject, String sendMsg, String sendTos) throws MessagingException;
}
