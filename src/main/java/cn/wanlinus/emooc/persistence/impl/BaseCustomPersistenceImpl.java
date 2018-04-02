package cn.wanlinus.emooc.persistence.impl;

import cn.wanlinus.emooc.persistence.custom.BaseCustomPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 用于提供EntityManager
 *
 * @author wanli
 * @date 2018-03-25 01:19
 */
@Component
public class BaseCustomPersistenceImpl implements BaseCustomPersistence {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

}
