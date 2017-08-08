package com.michal.dao.impl;

import com.michal.dao.api.DictionaryValuesDao;
import com.michal.dao.model.dictionary.DictionaryValues;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class DictionaryValueDaoImpl implements DictionaryValuesDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addDictionaryValue(DictionaryValues dictionaryValues) {
        entityManager.persist(dictionaryValues);
        entityManager.flush();
    }
}
