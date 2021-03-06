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

import cn.wanlinus.emooc.persistence.custom.BaseCustomPersistence;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 用于提供EntityManager
 *
 * @author wanli
 * @date 2018-03-25 01:19
 */
public class BaseCustomPersistenceImpl implements BaseCustomPersistence {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Map<String, Object>> nativeQuery(String nativeSql) {
        Query query = this.getEntityManager().createNativeQuery(nativeSql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> queryList = query.getResultList();
        return CollectionUtils.isEmpty(queryList) ? Collections.EMPTY_LIST : queryList;
    }

    @Override
    public <T> T genericsQuery(String nativeSql) {
        return null;
    }

}
