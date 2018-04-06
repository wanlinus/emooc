package cn.wanlinus.emooc.service;

import cn.wanlinus.emooc.domain.Teacher;
import cn.wanlinus.emooc.dto.TeacherDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author wanli
 * @date 2018-03-07 10:00
 */
public interface TeacherService {
    /**
     * 分页查询Teacher信息
     *
     * @param pageable 分页信息
     * @return 分页查找数据
     */
    Page<Teacher> pageTeacher(Pageable pageable);

    /**
     * 添加教师
     *
     * @param dto 教师数据传输对象
     * @return
     */
    Boolean addTeacher(TeacherDetailsDTO dto);
}
