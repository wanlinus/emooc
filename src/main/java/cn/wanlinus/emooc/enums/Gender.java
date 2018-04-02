package cn.wanlinus.emooc.enums;

/**
 * 用于描述性别的枚举类型
 *
 * @author wanli
 * @date 2018-02-22 10:42
 */
public enum Gender {
    MALE("男"),
    FEMALE("女"),
    UNDEFINE("未知");
    private String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
