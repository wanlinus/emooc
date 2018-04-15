package cn.wanlinus.emooc.service.impl;

import org.apache.commons.codec.digest.DigestUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wanli
 * @date 2018-02-26 18:46
 */
public class PasswordEncoderImpl implements PasswordEncoder {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String encode(CharSequence rawPassword) {
        return DigestUtils.md5Hex(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println(rawPassword.toString());
        logger.info(rawPassword.toString());
        logger.info(encodedPassword);
        return DigestUtils.md5Hex(rawPassword.toString()).equals(encodedPassword);
    }
}
