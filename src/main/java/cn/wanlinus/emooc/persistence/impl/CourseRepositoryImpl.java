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

package cn.wanlinus.emooc.persistence.impl;

import cn.wanlinus.emooc.domain.Course;
import cn.wanlinus.emooc.persistence.custom.CourseCustomPersistence;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.util.CollectionUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wanli
 * @date 2018-05-05 15:07
 */
public class CourseRepositoryImpl extends BaseCustomPersistenceImpl implements CourseCustomPersistence {
    @Override
    public List<Course> pageCourses(Integer pageSize, Integer page, String directionId, String classificationId) {
        String sql = null;
        if (classificationId != null) {
            sql = "SELECT * FROM TB_COURSE AS course INNER JOIN TB_COURSE_CLASSIFICATION AS classification " +
                    "ON course.COURSE_CLASSIFICATION_ID = classification.CLASSIFICATION_ID " +
                    "WHERE classification.CLASSIFICATION_ID = '" + classificationId + "' LIMIT " +
                    (page * pageSize) + ", " + pageSize;
        } else {
            sql = "SELECT * FROM  TB_COURSE AS course INNER JOIN " +
                    "(SELECT * FROM TB_COURSE_CLASSIFICATION AS cc " +
                    "WHERE cc.CLASSIFICATION_DIRECTION_ID = '" + directionId + "') AS classification " +
                    "ON course.COURSE_CLASSIFICATION_ID = classification.CLASSIFICATION_ID LIMIT " +
                    (page * pageSize) + ", " + pageSize + "";
        }
        System.err.println(sql);
        Query query = this.getEntityManager().createNativeQuery(sql);
        query.unwrap(SQLQuery.class).addEntity(Course.class);
        List<Course> list = query.getResultList();
        return CollectionUtils.isEmpty(list) ? Collections.EMPTY_LIST : list;

    }

    @Override
    public List<Map<String, Object>> courseDirectionPie() {
        final String sql = "SELECT d.DIRECTION_NAME AS dname,count(c.CLASSIFICATION_NAME) AS counts FROM TB_COURSE_CLASSIFICATION c " +
                "INNER JOIN TB_COURSE course ON c.CLASSIFICATION_ID = course.COURSE_CLASSIFICATION_ID " +
                "INNER JOIN TB_COURSE_DIRECTION d ON c.CLASSIFICATION_DIRECTION_ID = d.DIRECTION_ID " +
                "GROUP BY c.CLASSIFICATION_DIRECTION_ID";

        Query query = this.getEntityManager().createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> queryList = query.getResultList();
        return CollectionUtils.isEmpty(queryList) ? Collections.EMPTY_LIST : queryList;
    }
}
