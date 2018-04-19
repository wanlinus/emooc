/*
 * Copyright (C) 2018 - wanli <wanlinus@qq.com>
 *
 * This file is part of emooc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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

    /**
     * 对所有教师计数
     *
     * @return 教师总人数
     */
    Long countTeachers();
}
