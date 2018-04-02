package cn.wanlinus.emooc.commons;

import java.io.Serializable;

/**
 * 数据返回包装类
 *
 * @author wanli
 * @date 2018-03-31 12:38
 */
public class ResultData<T> implements Serializable {

    private Boolean code;

    private String message;

    private T data;

    public ResultData() {
    }

    public ResultData(Boolean code) {
        this.code = code;
    }

    public ResultData(Boolean code, String message) {
        this.code = code;
        this.message = message;
    }

    public Boolean getCode() {
        return code;
    }

    public void setCode(Boolean code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

