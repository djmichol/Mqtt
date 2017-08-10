package com.michal.dao.impl;

import com.michal.dao.api.SensorDao;
import com.michal.dao.model.homestructure.Room;
import com.michal.dao.model.networkstructure.Sensor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class SensorDaoImpl implements SensorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Sensor create(Sensor sensor) {
        entityManager.persist(sensor);
        entityManager.flush();
        return sensor;
    }

    @Transactional
    @Override
    public List<Sensor> getAllSensors() {
        return entityManager.createQuery("Select sensor from Sensor sensor").getResultList();
    }

    @Transactional
    @Override
    public Sensor getByTopic(String topic) {
        Query query = entityManager.createQuery("Select data from Sensor data where data.topic = :topic", Sensor.class);
        query.setParameter("topic", topic);
        return (Sensor) query.getSingleResult();
    }

    @Transactional
    @Override
    public Sensor get(Long id) {
        return entityManager.find(Sensor.class, id);
    }

    @Override
    @Transactional
    public boolean remove(Long id) {
        Sensor sensor = get(id);
        if (sensor != null) {
            entityManager.remove(sensor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public void addRoomToSensor(Room room, Long id) {
        Sensor sensor = get(id);
        sensor.setRoom(room);
        entityManager.merge(sensor);
        entityManager.flush();
    }
}