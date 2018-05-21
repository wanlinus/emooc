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

import cn.wanlinus.emooc.domain.CourseClassification;
import cn.wanlinus.emooc.domain.CourseDirection;
import cn.wanlinus.emooc.persistence.CourseClassificationRepository;
import cn.wanlinus.emooc.service.CourseClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wanli
 * @date 2018-04-22 21:12
 */
@Service
public class CourseClassificationServiceImpl implements CourseClassificationService {
    @Autowired
    private CourseClassificationRepository classificationRepository;

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<CourseClassification> getClassifications() {
        return classificationRepository.findAll();
    }

    @Override
    public CourseClassification get(String classificationId) {
        return classificationRepository.findOne(classificationId);
    }
}
