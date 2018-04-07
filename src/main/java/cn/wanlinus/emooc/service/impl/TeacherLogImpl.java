package cn.wanlinus.emooc.service.impl;

import cn.wanlinus.emooc.domain.TeacherLog;
import cn.wanlinus.emooc.persistence.TeacherLogRepository;
import cn.wanlinus.emooc.service.TeacherLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author wanli
 * @date 2018-03-07 10:40
 */
@Service
public class TeacherLogImpl implements TeacherLogService {
    @Autowired
    TeacherLogRepository teacherLogRepository;

    @Override
    public Page<TeacherLog> getTopNumberOrderByTimeDesc(Integer number) {
        return teacherLogRepository.findAll(
                new PageRequest(0, number, new Sort(Sort.Direction.DESC, "time")));
    }
}