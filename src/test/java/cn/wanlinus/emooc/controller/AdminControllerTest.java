package cn.wanlinus.emooc.controller;

import cn.wanlinus.emooc.domain.UserLog;
import cn.wanlinus.emooc.persistence.UserOperationLogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author wanli
 * @date 2018-03-06 14:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserOperationLogRepository userOperationLogRepository;

    @Test
    public void accessTeacherManager() throws Exception {
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "time"));
        Page<UserLog> userOperationLogs = userOperationLogRepository.findAll(pageable);
        System.out.println("-------------------------------------------");
        System.out.println(userOperationLogs.getTotalElements());
        for (UserLog log : userOperationLogs) {
            System.out.println(log);
        }
        System.out.println("-------------------------------------------");
    }
}
