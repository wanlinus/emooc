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

import cn.wanlinus.emooc.persistence.custom.UserCustomPersistence;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import javax.persistence.Query;
import java.util.*;

/**
 * @author wanli
 * @date 2018-03-25 01:31
 */
public class UserRepositoryImpl extends BaseCustomPersistenceImpl implements UserCustomPersistence {


    private static final String SELECT_GROUP_BY_GENDER = "SELECT user_gender AS 'gender',count(user_gender) AS 'number' FROM tb_user GROUP BY user_gender";

    @Deprecated
    public Map<String, Integer> genderPie() {
        Query query = this.getEntityManager().createNativeQuery(SELECT_GROUP_BY_GENDER);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> queryList = query.getResultList();
        Map<String, Integer> map = new HashMap<>(3);
        for (Map<String, Object> m : queryList) {
            map.put(m.get("gender").toString(), Integer.parseInt(m.get("number").toString()));
        }
        return map;
    }
}
