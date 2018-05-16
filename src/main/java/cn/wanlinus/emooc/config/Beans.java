package cn.wanlinus.emooc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Properties;

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
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailSenderHost);
        javaMailSender.setUsername(mailSenderUsername);
        javaMailSender.setPassword(mailSenderPassword);
        Properties properties = new Properties();
        //开启认证
        properties.setProperty("mail.smtp.auth", "true");
        //启用调试
        properties.setProperty("mail.debug", "false");
//        设置连接超时
        properties.setProperty("mail.smtp.timeout", "1000");
        properties.setProperty("mail.smtp.port", Integer.toString(465));
        ////设置ssl端口
        properties.setProperty("mail.smtp.socketFactory.port", Integer.toString(465));
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Message message() throws MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String sslFactory = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", mailSenderHost);
        props.setProperty("mail.smtp.socketFactory.class", sslFactory);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailSenderUsername, mailSenderPassword);
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(mailFrom));
        return msg;
    }
}
