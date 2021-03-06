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

package cn.wanlinus.emooc.service.impl;

import cn.wanlinus.emooc.annotation.UserAnnotation;
import cn.wanlinus.emooc.commons.ResultData;
import cn.wanlinus.emooc.domain.*;
import cn.wanlinus.emooc.dto.*;
import cn.wanlinus.emooc.enums.EmoocCourseGrade;
import cn.wanlinus.emooc.enums.EmoocLogType;
import cn.wanlinus.emooc.persistence.CourseRepository;
import cn.wanlinus.emooc.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static cn.wanlinus.emooc.utils.CommonUtils.*;

/**
 * @author wanli
 * @date 2018-04-19 23:59
 */
@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    /**
     * 课程持久对象
     */
    @Autowired
    private CourseRepository courseRepository;

    /**
     * 课程章节服务对象
     */
    @Autowired
    private CourseSectionService sectionService;

    /**
     * 课程方向服务对象
     */
    @Autowired
    private CourseDirectionService directionService;

    /**
     * 课程类型服务对象
     */
    @Autowired
    private CourseClassificationService classificationService;

    /**
     * 课程视频服务对象
     */
    @Autowired
    private CourseVideoService videoService;

    /**
     * 课程类型服务对象
     */
    @Autowired
    private CourseTypeService typeService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private CourseScoreService scoreService;

    /**
     * 用户学习服务对象
     */
    @Autowired
    private UserStudyService userStudyService;

    /**
     * 评论服务对象
     */
    @Autowired
    private CourseCommentService commentService;

    /**
     * 日志服务对象
     */
    @Autowired
    private EmoocLogService logService;


    @Override
    public List<CourseDirection> getCourseDirections() {
        return directionService.getAllDirections();
    }

    @Override
    public List<CourseDirectionDTO> getCourseDirectionDTOs(String directionId) {
        List<CourseDirectionDTO> list = new ArrayList<>();
        for (CourseDirection d : getCourseDirections()) {
            CourseDirectionDTO dto = new CourseDirectionDTO();
            dto.setId(d.getId());
            dto.setName(d.getName());
            if (d.getId().equals(directionId)) {
                dto.setFlag(true);
            } else {
                dto.setFlag(false);
            }
            list.add(dto);
        }
        return list;
    }

    @Override
    public Course saveCourse(Teacher teacher, ThAddCourseDTO dto) {
        Course course = new Course();
        course.setId(cid());
        course.setName(dto.getName());
        course.setTariff(dto.getTariff());
        course.setGrade(EmoocCourseGrade.values()[dto.getGrade()]);
        course.setDuration(0);
        course.setScore(0.0);
        course.setIntro(dto.getIntro());
        course.setNotice(dto.getNotice());
        course.setWtcanlearn(dto.getWtcanlearn());
        course.setImagePath(dto.getPath());
        course.setTeacher(teacher);
        course.setCreateTime(new Date());
        course.setClassification(classificationService.get(String.valueOf(dto.getClassification())));
        course.setType(typeService.get(String.valueOf(dto.getType())));
        return courseRepository.save(course);
    }

    @Override
    public List<Course> topCourse(Teacher teacher) {
        return courseRepository.findTopByTeacherId(teacher.getId(), 5);

    }

    @Override
    public List<ThCourseDTO> pageCourse(Teacher teacher, Pageable pageable) {
        List<ThCourseDTO> dtoList = new ArrayList<>();
        List<Course> pages = courseRepository.pageCourses(teacher.getId(), pageable.getOffset(), pageable.getPageSize());
        if (pages != null && !pages.isEmpty()) {
            for (Course c : pages) {
                ThCourseDTO dto = new ThCourseDTO();
                dto.setId(c.getId());
                dto.setName(c.getName());
                dto.setClassification(c.getClassification().getName());
                dto.setComments(commentService.count(c.getId()));
                dto.setDate(dateFormatSimple(c.getCreateTime()));
                dto.setPicPath(c.getImagePath());
                dto.setScore(c.getScore());
                dto.setStudy(userStudyService.countStudies(c.getId()));
                dto.setNotice(c.getNotice());
                dtoList.add(dto);
            }
        } else {
            dtoList = null;
        }
        return dtoList;
    }

    @Override
    public List<Course> pageCourse(Integer pageSize, Integer page, String directionId, String classificationId) {
        return courseRepository.pageCourses(pageSize, page, directionId, classificationId);
    }


    @Override
    public Course getCourse(String courseId) {
        return courseRepository.findOne(courseId);
    }

    @Override
    public Long countCourses() {
        return courseRepository.count();

    }

    @Override
    public Long currentDayNewlyIncreased() {
        return courseRepository.courseNewlyIncreased(new Date());

    }

    @Override
    public List<Long> coursesAddStatistics(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(logService.countCourseLogs(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourseSection addSection(SectionAddDTO dto) {
        CourseSection section = new CourseSection();
        section.setId(csid());
        section.setIndex(sectionService.countCourseSection(dto.getCourseId()) + 1);
        section.setCourse(courseRepository.findOne(dto.getCourseId()));
        section.setDetail(dto.getDescription());
        section.setName(dto.getTitle());
        section.setCreateTime(new Date());
        return sectionService.save(section);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Long countCourseSectionVideos() {
        return videoService.countVideos();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CourseVideo addSectionVideo(CourseSectionVideoAddDTO dto) {
        CourseVideo video = new CourseVideo();
        video.setId(csvid());
        video.setName(dto.getName());
        //先设置为0,以后能够处理视频文件再细化
        video.setDuration(0);
        video.setPath(dto.getVideoPath());
        video.setSha1(dto.getSha1());
        video.setCreateTime(new Date());
        CourseSection section = sectionService.find(dto.getSectionId());
        video.setSection(section);
        return videoService.saveVideo(video);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Long> courseVideosStatistics(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(logService.countVideoLogs(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Long currentDayVideoNewlyIncreased() {
        return videoService.countVideos(new Date());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public CourseVideo getCourseVideo(String videoId) {
        return videoService.findVideo(videoId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<CourseClassification> getClassifications() {
        return classificationService.getClassifications();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<CourseClassificationDTO> getClassifications(String classificationId) {
        List<CourseClassificationDTO> list = new ArrayList<>();
        for (CourseClassification c : classificationService.get(classificationId).getDirection().getClassifications()) {
            CourseClassificationDTO dto = new CourseClassificationDTO();
            dto.setId(c.getId());
            dto.setName(c.getName());
            if (c.getId().equals(classificationId)) {
                dto.setFlag(true);
            } else {
                dto.setFlag(false);
            }
            list.add(dto);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Course> pageCourse(String directionId) {
        CourseDirection direction = directionService.getDirection(directionId);
        List<Course> courses = new ArrayList<>();
        for (CourseClassification classification : direction.getClassifications()) {
            courses.addAll(classification.getCourses());
        }
        return courses;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Course> getAllCoursesDescDate() {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return courseRepository.findAll(sort);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public CourseDirection getCourseDirection(String directionId) {
        return directionService.getDirection(directionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Course> getTopCourses(String teacherId, Integer number) {
        return courseRepository.findTopByTeacherId(teacherId, number);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<CourseDirectionDTO> getDirectionByClassification(String classificationId) {
        CourseDirection direction = classificationService.get(classificationId).getDirection();
        List<CourseDirectionDTO> list = new ArrayList<>();
        for (CourseDirection d : getCourseDirections()) {
            CourseDirectionDTO dto = new CourseDirectionDTO();
            dto.setName(d.getName());
            dto.setId(d.getId());
            if (d.getId().equals(direction.getId())) {
                dto.setFlag(true);
            } else {
                dto.setFlag(false);
            }
            list.add(dto);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<CourseClassificationDTO> getClassificationDTOs(String classificationId) {
        return classification2DTO(classificationService.get(classificationId).getDirection().getClassifications(), classificationId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<CourseClassificationDTO> getDirectionDTOsByClassification(String directionId) {
        return classification2DTO(directionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public CourseClassificationListDTO getClassificationDTOList(String classificationId) {
        CourseClassificationListDTO listDTO = new CourseClassificationListDTO();
        List<CourseClassificationDTO> list = null;
        CourseDirection direction = classificationService.get(classificationId).getDirection();
        list = classification2DTO(direction.getClassifications(), classificationId);
        listDTO.setList(list);
        listDTO.setId(direction.getId());
        return listDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public CourseClassificationListDTO getClassificationDTOListByDirection(String directionId) {
        CourseClassificationListDTO listDTO = new CourseClassificationListDTO();
        listDTO.setList(classification2DTO(directionId));
        listDTO.setId(directionId);
        return listDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Course> recommendCourse() {
        return courseRepository.randCourse();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @UserAnnotation(type = EmoocLogType.USER_ADD_QUESTION)
    public ResultData<QuesNoteScoreReturnDTO> addQuestion(String courseId, String msg) {
        ResultData<QuesNoteScoreReturnDTO> resultData = new ResultData<>();
        try {
            Question q = questionService.addQuestion(msg, courseRepository.getOne(courseId));
            QuesNoteScoreReturnDTO dto = new QuesNoteScoreReturnDTO();
            dto.setUsername(q.getUser().getUsername());
            dto.setAvatar(q.getUser().getAvatar());
            dto.setTime(dateFormatComplex(new Date()));
            resultData.setCode(true);
            resultData.setMessage("提问成功");
            resultData.setData(dto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultData.setCode(false);
            resultData.setMessage("提问失败");
        }
        return resultData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @UserAnnotation(type = EmoocLogType.USER_ADD_NOTE)
    public ResultData<QuesNoteScoreReturnDTO> addNote(NoteDTO dto) {
        ResultData<QuesNoteScoreReturnDTO> resultData = new ResultData<>();
        try {
            Note note = noteService.addNote(dto.getNote(), courseRepository.getOne(dto.getCourseId()));
            QuesNoteScoreReturnDTO re = new QuesNoteScoreReturnDTO();
            re.setUsername(note.getUser().getUsername());
            re.setAvatar(note.getUser().getAvatar());
            re.setTime(dateFormatComplex(new Date()));
            resultData.setCode(true);
            resultData.setMessage("添加成功");
            resultData.setData(re);
        } catch (Exception e) {
            resultData.setCode(false);
            resultData.setMessage("添加失败");
            logger.error(e.getMessage());
        }
        return resultData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<Map<String, Object>> courseDirectionPie() {
        return courseRepository.courseDirectionPie();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @UserAnnotation(type = EmoocLogType.USER_ADD_SCORE)
    public ResultData<QuesNoteScoreReturnDTO> addScore(CourseScoreDTO dto) {
        ResultData<QuesNoteScoreReturnDTO> resultData = new ResultData<>();

        try {
            CourseScore score = new CourseScore();
            score.setId(sid());
            score.setCourse(getCourse(dto.getCourseId()));
            score.setGrade(dto.getScore());
            score.setTime(new Date());
            score.setUser(userService.getCurrentUser());
            score.setWishes(dto.getWishes());
            CourseScore courseScore = scoreService.saveScore(score);

            resultData.setCode(true);
            resultData.setMessage("评论成功");
            QuesNoteScoreReturnDTO returnDto = new QuesNoteScoreReturnDTO();
            returnDto.setTime(dateFormatComplex(new Date()));
            returnDto.setAvatar(courseScore.getUser().getAvatar());
            returnDto.setUsername(courseScore.getUser().getUsername());
            resultData.setData(returnDto);
        } catch (Exception e) {
            resultData.setCode(false);
            resultData.setMessage("评分出现错误");
            logger.error(e.getMessage());
        }
        return resultData;
    }

    private List<CourseClassificationDTO> classification2DTO(String directionId) {
        List<CourseClassificationDTO> list = new ArrayList<>();
        for (CourseClassification c : directionService.getDirection(directionId).getClassifications()) {
            CourseClassificationDTO dto = new CourseClassificationDTO();
            dto.setId(c.getId());
            dto.setFlag(false);
            dto.setName(c.getName());
            list.add(dto);
        }
        return list;
    }

    private List<CourseClassificationDTO> classification2DTO(List<CourseClassification> classifications, String compareId) {
        List<CourseClassificationDTO> list = new ArrayList<>();
        for (CourseClassification c : classifications) {
            CourseClassificationDTO dto = new CourseClassificationDTO();
            dto.setId(c.getId());
            dto.setName(c.getName());
            if (compareId.equals(c.getId())) {
                dto.setFlag(true);
            } else {
                dto.setFlag(false);
            }
            list.add(dto);
        }
        return list;
    }
}
