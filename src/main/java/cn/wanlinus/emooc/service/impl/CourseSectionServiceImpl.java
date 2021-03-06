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

import cn.wanlinus.emooc.domain.CourseSection;
import cn.wanlinus.emooc.persistence.CourseSectionRepository;
import cn.wanlinus.emooc.service.CourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wanli
 * @date 2018-05-03 18:52
 */
@Service
public class CourseSectionServiceImpl implements CourseSectionService {

    @Autowired
    private CourseSectionRepository sectionRepository;

    @Override
    public Integer countCourseSection(String courseId) {
        return sectionRepository.countCourseSectionByCourseId(courseId);
    }

    @Override
    public CourseSection save(CourseSection section) {
        return sectionRepository.save(section);
    }

    @Override
    public CourseSection find(String sectionId) {
        return sectionRepository.findOne(sectionId);
    }
}
