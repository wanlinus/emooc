package cn.wanlinus.emooc.enums;

/**
 * @author wanli
 * @date 2018-04-16 16:54
 */
public enum EmoocRole {
    ROLE_USER("USER", "普通用户"),
    ROLE_TEACHER("TEACHER", "讲师"),
    ROLE_ADMIN("ADMIN", "管理员"),
    ROLE_UNKNOWN("UNKNOWN", "未知");

    private String desc;
    private String cn;

    EmoocRole(String desc, String cn) {
        this.desc = desc;
        this.cn = cn;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    @Override
    public String toString() {
        return "EmoocRole{" +
                "desc='" + desc + '\'' +
                ", cn='" + cn + '\'' +
                '}';
    }
}
