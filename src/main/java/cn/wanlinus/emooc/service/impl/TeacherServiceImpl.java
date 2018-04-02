package cn.wanlinus.emooc.service.impl;

import cn.wanlinus.emooc.domain.Teacher;
import cn.wanlinus.emooc.exception.TeacherServiceException;
import cn.wanlinus.emooc.persistence.TeacherRepository;
import cn.wanlinus.emooc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wanli
 * @date 2018-03-07 10:00
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Page<Teacher> pageTeacher(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }
}
