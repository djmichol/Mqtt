package com.michal.dao.impl;

import com.michal.dao.api.ActionDao;
import com.michal.dao.model.rule.Action;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class ActionDaoImpl implements ActionDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Action create(Action node) {
        entityManager.persist(node);
        entityManager.flush();
        return node;
    }

    @Transactional
    @Override
    public List<Action> getAll() {
        return entityManager.createQuery("Select action from Action action").getResultList();
    }

    @Transactional
    @Override
    public Action get(Long id) {
        return entityManager.find(Action.class, id);
    }

    @Override
    @Transactional
    public boolean remove(Long id) {
        Action node = get(id);
        if (node != null) {
            entityManager.remove(node);
            return true;
        } else {
            return false;
        }
    }

}
