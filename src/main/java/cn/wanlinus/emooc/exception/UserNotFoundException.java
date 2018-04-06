package cn.wanlinus.emooc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 用户未找到
 *
 * @author wanli
 * @date 2018-04-05 16:37
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "user Not Found")
public class UserNotFoundException extends RuntimeException {
    private String id;

    public UserNotFoundException(String message, String id) {
        super(message);
        this.id = id;
    }

    public UserNotFoundException(String id) {
        this.id = id;
    }
}
