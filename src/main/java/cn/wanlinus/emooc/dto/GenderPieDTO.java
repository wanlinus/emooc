package cn.wanlinus.emooc.dto;

import java.io.Serializable;

/**
 * 性别饼状图数据传输对象
 *
 * @author wanli
 * @date 2018-3-25 05:10:35
 */
public class GenderPieDTO implements Serializable {
    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public GenderPieDTO() {
    }

    public GenderPieDTO(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "GenderPieDTO{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
