package cn.wanlinus.emooc;

import cn.wanlinus.emooc.persistence.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmoocApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private UserRepository userRepository;



}
