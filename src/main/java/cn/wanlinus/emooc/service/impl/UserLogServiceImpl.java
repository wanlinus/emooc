package cn.wanlinus.emooc.service.impl;

import cn.wanlinus.emooc.domain.UserLog;
import cn.wanlinus.emooc.persistence.UserLogRepository;
import cn.wanlinus.emooc.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author wanli
 * @date 2018-03-06 17:39
 */
@Service
public class UserLogServiceImpl implements UserLogService {


    @Autowired
    private UserLogRepository userLogRepository;

    @Override
    public Page<UserLog> getTopNumberOrderByTimeDesc(Integer number) {
        return userLogRepository.findAll(
                new PageRequest(0, number, new Sort(Sort.Direction.DESC, "time")));
    }
}
