package cn.wanlinus.emooc.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanli
 * @date 2018-04-06 14:06
 */
public class LayuiPaginationDataDTO<T> implements Serializable {
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;

    public LayuiPaginationDataDTO() {
    }

    public LayuiPaginationDataDTO(Integer code, String msg, Integer count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LayuiPaginationDataDTO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
