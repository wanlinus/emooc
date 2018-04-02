package cn.wanlinus.emooc;

/**
 * @author wanli
 * @date 2018-04-01 01:19
 */
public class Main {
    public static void main(String[] args) {
        String str = "Mozilla/5.0 (Linux; Android 8.0.0; LON-AL00 Build/HUAWEILON-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.109 Mobile Safari/537.36";
        System.out.println(str.substring(str.indexOf("(") + 1, str.indexOf(")")));
    }
}
