package com.michal.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.michal.dao.api.SensorDataDao;
import com.michal.dao.model.mqttdata.SensorData;

public class SensorDataDaoImpl implements SensorDataDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public SensorData create(SensorData data) {
        entityManager.persist(data);
        entityManager.flush();
        return data;
    }

    @Override
    @Transactional
    public List<SensorData> getAllData() {
        return entityManager.createQuery("Select data from SensorData data").getResultList();
    }

    @Override
    public List<SensorData> getAllDataFrom(Date from) {
        Query query = entityManager.createQuery("Select data from SensorData data where data.timestamp = :dateFrom", SensorData.class);
        query.setParameter("dateFrom", from);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<SensorData> getDataByType(String type) {
        Query query = entityManager.createQuery("Select data from SensorData data where data.dataType = :type", SensorData.class);
        query.setParameter("type", type);
        return query.getResultList();
    }
}
