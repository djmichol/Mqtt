package com.michal.dao.impl;

import com.michal.dao.api.ActionDao;
import com.michal.dao.model.rule.Action;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public List<Action> getForRule(Long ruleId) {
        Query query = entityManager.createQuery("Select rule.actions from GroovyRule rule where rule.id=:ruleId");
        query.setParameter("ruleId", ruleId);
        return query.getResultList();
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
