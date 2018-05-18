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

package cn.wanlinus.emooc.task;

import cn.wanlinus.emooc.domain.Course;
import cn.wanlinus.emooc.domain.EmoocTask;
import cn.wanlinus.emooc.persistence.CourseRepository;
import cn.wanlinus.emooc.persistence.CourseScoreRepository;
import cn.wanlinus.emooc.persistence.CourseSectionRepository;
import cn.wanlinus.emooc.persistence.EmoocTaskRepository;
import cn.wanlinus.emooc.service.CourseScoreService;
import cn.wanlinus.emooc.service.CourseService;
import cn.wanlinus.emooc.utils.CommonUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static cn.wanlinus.emooc.utils.CommonUtils.taskid;

/**
 * Emooc时间调度类
 *
 * @author wanli
 * @date 2018-05-18 12:13
 */
@Component
public class EmoocTimerTask {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseScoreService scoreService;

    @Autowired
    private EmoocTaskRepository taskRepository;

    /**
     * 每小时更新TB_COURSE表中课程评分字段
     */
    @Scheduled(cron = "0 0 * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void courseScore() {
        List<Map<String, Object>> scores = scoreService.courseScores();
        for (Map<String, Object> map : scores) {
            Course course = courseService.getCourse(String.valueOf(map.get("courseId")));
            course.setScore(Double.parseDouble(String.valueOf(map.get("avgScore"))));
        }
        EmoocTask task = new EmoocTask();
        task.setId(taskid());
        task.setDate(new Date());
        task.setDetail(JSON.toJSONString(scores));
        taskRepository.save(task);
    }
}
