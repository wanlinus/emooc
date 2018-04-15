package cn.wanlinus.emooc.service.impl;

import cn.wanlinus.emooc.annotation.AdminOperation;
import cn.wanlinus.emooc.domain.Teacher;
import cn.wanlinus.emooc.dto.TeacherDetailsDTO;
import cn.wanlinus.emooc.enums.Gender;
import cn.wanlinus.emooc.enums.TeacherStatus;
import cn.wanlinus.emooc.exception.TeacherServiceException;
import cn.wanlinus.emooc.persistence.TeacherRepository;
import cn.wanlinus.emooc.service.TeacherService;
import cn.wanlinus.emooc.utils.CommonUtils;
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


    @Override
    @AdminOperation(descript = "注册教师")
    @Transactional(rollbackFor = Exception.class)
    public Boolean addTeacher(TeacherDetailsDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setUsername(dto.getUsername());
        teacher.setEmail(dto.getEmail());
        teacher.setPassword(CommonUtils.md5Encrypt(dto.getPassword()));
        teacher.setId(CommonUtils.tid());
        teacher.setAvatar("");
        teacher.setGender(Gender.UNDEFINE);
        teacher.setStatus(TeacherStatus.A);
        teacher.setDetail("");
        teacher.setPosition("讲师");
        teacher.setSignature("这位老师很懒,暂时没有签名");
        return teacherRepository.save(teacher) != null;
    }

    @Override
    public Long countTeachers() {
        return teacherRepository.count();
    }
}
