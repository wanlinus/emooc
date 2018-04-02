package cn.wanlinus.emooc.service;

import cn.wanlinus.emooc.dto.GenderPieDTO;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wanli
 * @date 2018-03-25 02:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test

    public void genderPieTest() {
        List<GenderPieDTO> list = service.genderPie();
        for (GenderPieDTO dto : list) {
            System.out.println(JSON.toJSON(dto));
        }
    }
}
