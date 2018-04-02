package cn.wanlinus.emooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author wanli
 * @date 2018-2-22 09:59:18
 */
@SpringBootApplication
@EnableCaching
public class EmoocApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmoocApplication.class, args);
    }
}
