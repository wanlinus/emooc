package cn.wanlinus.emooc.enums;

/**
 * @author wanli
 * @date 2018-03-07 09:52
 */
public enum TeacherStatus {
    A("启用"),
    B("应用");
    private String name;

    TeacherStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
