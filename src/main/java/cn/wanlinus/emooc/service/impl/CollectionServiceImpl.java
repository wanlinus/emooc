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

import cn.wanlinus.emooc.persistence.CollectionRepository;
import cn.wanlinus.emooc.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static cn.wanlinus.emooc.utils.CommonUtils.colid;

/**
 * @author wanli
 * @date 2018/5/11
 */
@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addCollection(String uid, String courseId) {
        return collectionRepository.save(colid(), uid, courseId);
    }

    @Override
    public Integer removeCollection(String uid, String courseId) {
        return collectionRepository.deleteByUserIdAndCourseId(uid, courseId);
    }
}
