package com.michal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.michal.dao.SensorDataDao;
import com.michal.dao.model.SensorData;

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
        return entityManager.createQuery("from SensorData").getResultList();
    }

    @Override
    @Transactional
    public List<SensorData> getDataByType(String type) {
        Query query = entityManager.createQuery("Select data from SensorData data where data.dataType = :type", SensorData.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<SensorData> getDataForRoomByType(String room, String type) {
        String searchQuery = "Select data from SensorData data where data.dataType = :type ";
        if (room != null) {
            searchQuery += " and data.dataRoom=:dataRoom";
        }
        Query query = entityManager.createQuery(searchQuery, SensorData.class);
        query.setParameter("type", type);
        if (room != null) {
            query.setParameter("dataRoom", room);
        }
        return query.getResultList();
    }

    @Override
    public List<SensorData> getLatestData() {
        String searchQuery = "Select data.dataType, data.dataRoom, MAX(data.dataTimestamp) from SensorData data group by data.dataType, data.dataRoom ";
        Query query = entityManager.createQuery(searchQuery);
        List<Object[]> objects = query.getResultList();
        List<SensorData> results = new ArrayList<>();
        Query query2 = entityManager.createQuery("Select data from SensorData data where data.dataType = :type and data.dataRoom=:dataRoom and data.dataTimestamp=:time",
                SensorData.class);

        objects.forEach(o -> {
            query2.setParameter("type", o[0]);
            query2.setParameter("dataRoom", o[1]);
            query2.setParameter("time", o[2]);
            results.add((SensorData) query2.getSingleResult());
        });
        return results;
    }
}
