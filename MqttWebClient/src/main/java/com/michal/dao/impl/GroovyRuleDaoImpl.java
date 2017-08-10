package com.michal.dao.impl;

import com.michal.dao.api.GroovyRuleDao;
import com.michal.dao.model.rule.GroovyRule;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


public class GroovyRuleDaoImpl implements GroovyRuleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public GroovyRule create(GroovyRule groovyRule) {
        entityManager.persist(groovyRule);
        entityManager.flush();
        return groovyRule;
    }

    @Transactional
    @Override
    public List<GroovyRule> getAllNodes() {
        return entityManager.createQuery("from GroovyRule").getResultList();
    }

    @Transactional
    @Override
    public GroovyRule get(Long id) {
        return entityManager.find(GroovyRule.class, id);
    }

    @Override
    @Transactional
    public boolean remove(Long id) {
        GroovyRule groovyRule = get(id);
        if (groovyRule != null) {
            entityManager.remove(groovyRule);
            return true;
        } else {
            return false;
        }
    }

}
