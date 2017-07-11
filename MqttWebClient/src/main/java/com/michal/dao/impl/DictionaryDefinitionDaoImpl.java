package com.michal.dao.impl;


import com.michal.dao.DictionaryDefinitionDao;
import com.michal.dao.model.DictionaryDefinition;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class DictionaryDefinitionDaoImpl implements DictionaryDefinitionDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public DictionaryDefinition addDictoinaryDefinition(DictionaryDefinition dictionaryDefinition) {
        entityManager.persist(dictionaryDefinition);
        entityManager.flush();
        return dictionaryDefinition;
    }

    @Override
    @Transactional
    public DictionaryDefinition getDictionaryDefinitionByCode(String code) {
        Query query = entityManager.createQuery("Select data from DictionaryDefinition data where data.dictDefCode = :code", DictionaryDefinition.class);
        query.setParameter("code", code);
        return (DictionaryDefinition) query.getSingleResult();
    }

    @Override
    public List<DictionaryDefinition> getAllDefinitions() {
        return entityManager.createQuery("from DictionaryDefinition").getResultList();
    }
}
