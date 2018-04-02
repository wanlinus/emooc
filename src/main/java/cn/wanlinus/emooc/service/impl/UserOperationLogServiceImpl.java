package cn.wanlinus.emooc.service.impl;

import cn.wanlinus.emooc.domain.UserOperationLog;
import cn.wanlinus.emooc.persistence.UserOperationLogRepository;
import cn.wanlinus.emooc.service.UserOperationLogService;
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
public class UserOperationLogServiceImpl implements UserOperationLogService {


    @Autowired
    private UserOperationLogRepository userOperationLogRepository;

    @Override
    public Page<UserOperationLog> getTopNumberOrderByTimeDesc(Integer number) {
        return userOperationLogRepository.findAll(
                new PageRequest(0, number, new Sort(Sort.Direction.DESC, "time")));
    }
}
