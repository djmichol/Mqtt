package com.michal.dao.impl;

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
		Query query = entityManager.createQuery("Select data from SensorData data where data.dataType = :type and data.dataRoom=:dataRoom", SensorData.class);
		query.setParameter("type", type);
		query.setParameter("dataRoom", room);
		return query.getResultList();
	}
}
