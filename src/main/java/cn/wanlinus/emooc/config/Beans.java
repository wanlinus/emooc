package cn.wanlinus.emooc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author wanli
 * @date 2018-05-09 20:46
 */
@Configuration
public class Beans {

    @Value("${mail.from}")
    private String mailFrom;
    @Value("${mail.host}")
    private String mailSenderHost;
    @Value("${mail.username}")
    private String mailSenderUsername;
    @Value("${mail.password}")
    private String mailSenderPassword;

    /**
     * 用于发送邮件的内容 bean
     * 需要设置 subject
     */
    @Bean
    public SimpleMailMessage simpleMailMessage() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailFrom);
        return mailMessage;
    }

    /**
     * 用于发送邮件的Bean
     */
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailSenderHost);
        javaMailSender.setUsername(mailSenderUsername);
        javaMailSender.setPassword(mailSenderPassword);
        return javaMailSender;
    }
}
