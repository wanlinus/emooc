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

    @Override
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
