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

package cn.wanlinus.emooc.persistence;

import cn.wanlinus.emooc.domain.Course;
import cn.wanlinus.emooc.persistence.custom.CourseCustomPersistence;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wanli
 * @date 2018-04-15 13:54
 */
public interface CourseRepository extends BaseRepository<Course, String>, CourseCustomPersistence {
    /**
     * 通过教师Id查找该教师顶置课程
     *
     * @param teacherId 教师ID
     * @param number    指定个数
     * @return 课程列表
     */
    @Query(value = "SELECT * FROM TB_COURSE AS c WHERE c.COURSE_TEACH_ID = ?1 ORDER BY c.COURSE_SCORE DESC LIMIT 0,?2", nativeQuery = true)
    List<Course> findTopByTeacherId(String teacherId, Integer number);


    /**
     * 分页查询教师课程
     *
     * @param id       教师ID
     * @param offset   偏移量
     * @param pageSize 每页数量
     * @return 分页信息
     */
    @Query(value = "SELECT * FROM TB_COURSE AS c WHERE c.COURSE_TEACH_ID = ?1 ORDER BY c.COURSE_CREATE_TIME DESC LIMIT ?2, ?3", nativeQuery = true)
    List<Course> pageCourses(String id, int offset, int pageSize);

    /**
     * 查询某日新增课程
     *
     * @param date 指定日期
     * @return 新增课程数
     */
    @Query(value = "SELECT count(*) FROM TB_COURSE AS c WHERE  date_format(c.COURSE_CREATE_TIME, '%Y-%m-%d') = date_format(?1, '%Y-%m-%d')", nativeQuery = true)
    Long courseNewlyIncreased(Date date);

    /**
     * 从数据库随机取数据
     *
     * @return 课程列表
     */
    @Query(value = "SELECT * FROM TB_COURSE ORDER BY rand() LIMIT 0,5", nativeQuery = true)
    List<Course> randCourse();
}
