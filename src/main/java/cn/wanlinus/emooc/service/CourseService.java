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

import cn.wanlinus.emooc.domain.*;
import cn.wanlinus.emooc.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * 课程服务接口
 *
 * @author wanli
 * @date 2018-04-19 23:57
 */
public interface CourseService {
    /**
     * 获取所有的课程方向
     *
     * @return 课程方向列表
     */
    List<CourseDirection> getCourseDirections();

    /**
     * 获取包含选择信息的课程方向
     *
     * @param directionId 课程方向传输对象列表
     * @return 课程方向数据传输对象
     */
    List<CourseDirectionDTO> getCourseDirectionDTOs(String directionId);

    /**
     * 保存课程
     *
     * @param teacher 保存课程的教师
     * @param dto     保存课程的数据传输对象
     * @return 保存完成的课程对象
     */
    Course saveCourse(Teacher teacher, ThAddCourseDTO dto);

    /**
     * 查找指定讲师的顶置课程
     *
     * @param teacher 指定讲师
     * @return 课程列表
     */
    List<Course> topCourse(Teacher teacher);


    /**
     * 对相应教师的课程分页
     *
     * @param teacher  相应教师
     * @param pageable 分页信息
     * @return ThCourseDTO
     */
    List<ThCourseDTO> pageCourse(Teacher teacher, Pageable pageable);


    /**
     * 对课程进行筛选分页
     *
     * @param pageSize         每页条数
     * @param page             第几页
     * @param directionId      方向Id
     * @param classificationId 分类Id
     * @return 课程集合
     */
    List<Course> pageCourse(Integer pageSize, Integer page, String directionId, String classificationId);

    /**
     * 根据课程Id获取课程
     *
     * @param courseId 课程ID
     * @return 相应课程
     */
    Course getCourse(String courseId);

    /**
     * 对所有课程计数
     *
     * @return 课程数
     */
    Long countCourses();

    /**
     * 今日新增课程
     *
     * @return 新增课程数
     */
    Long currentDayNewlyIncreased();

    /**
     * 统计传入日期前days天课程添加数组
     *
     * @param date 传入日期
     * @param days 天数
     * @return 新增课程数组
     */
    List<Long> coursesAddStatistics(Date date, Integer days);

    /**
     * 添加课程章节
     *
     * @param dto 添加课程章节数据传输对象
     * @return 添加的课程
     */
    CourseSection addSection(SectionAddDTO dto);

    /**
     * 对所有课程Video计数
     *
     * @return
     */
    Long countCourseSectionVideos();


    /**
     * 添加课程章节视频
     *
     * @param dto 食品添加传输对象
     * @return 课程视频对象
     */
    CourseVideo addSectionVideo(CourseSectionVideoAddDTO dto);

    /**
     * 对课程视频进行统计
     *
     * @param date 传入日期
     * @param days 天数
     * @return 统计量
     */
    List<Long> courseVideosStatistics(Date date, int days);

    /**
     * 当日课程视频添加总数
     *
     * @return 统计量
     */
    Long currentDayVideoNewlyIncreased();

    /**
     * 获取视频对象
     *
     * @param videoId 视频ID
     * @return 视频对象
     */
    CourseVideo getCourseVideo(String videoId);

    /**
     * 获取所有的课程分类
     *
     * @return 课程分类列表
     */
    List<CourseClassification> getClassifications();

    /**
     * 获取所有课程分类
     *
     * @param classificationId 是否着重标记
     * @return 课程分类列表
     */
    List<CourseClassificationDTO> getClassifications(String classificationId);

    /**
     * 获得所有课程
     *
     * @return 课程列表
     */
    List<Course> getAllCourses();

    /**
     * 获取指定条件课程
     *
     * @param directionId 课程方向
     * @return 课程列表
     */
    List<Course> pageCourse(String directionId);

    /**
     * 获取所有课程 根据时间倒叙排序
     *
     * @return 课程列表
     */
    List<Course> getAllCoursesDescDate();

    /**
     * 获取指定课程方向
     *
     * @param directionId 指定课程方向ID
     * @return 相应的课程
     */
    CourseDirection getCourseDirection(String directionId);

    /**
     * 获取指定教室排行课程
     *
     * @param teacherId 指定讲师
     * @param number    个数
     * @return 相应课程集合
     */
    List<Course> getTopCourses(String teacherId, Integer number);

    /**
     * 通过课程分类查找课程方向
     *
     * @param classificationId 课程分类Id
     * @return 课程方向数据传输对象列表
     */
    List<CourseDirectionDTO> getDirectionByClassification(String classificationId);

    /**
     * 查询课程分类数据传输对象
     *
     * @param classificationId 课程分类ID
     * @return 课程分类数据传输对象
     */
    List<CourseClassificationDTO> getClassificationDTOs(String classificationId);

    /**
     * 通过方向ID查找课程分类数据传输对象
     *
     * @param directionId 方向ID
     * @return 课程分类数据传输对象
     */
    List<CourseClassificationDTO> getDirectionDTOsByClassification(String directionId);

    /**
     * 查询课程分类列表数据传输对象
     *
     * @param classificationId 课程分类ID
     * @return 课程分类列表数据传输对象
     */
    CourseClassificationListDTO getClassificationDTOList(String classificationId);

    /**
     * 通过方向ID查找课程分类列表数据传输对象
     *
     * @param directionId 方向Id
     * @return 课程分类列表数据传输对象
     */
    CourseClassificationListDTO getClassificationDTOListByDirection(String directionId);

    /**
     * 推荐课程
     *
     * @return 返回5个推荐课程
     */
    List<Course> recommendCourse();
}
