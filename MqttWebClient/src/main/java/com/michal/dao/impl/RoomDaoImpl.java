package com.michal.dao.impl;

import com.michal.dao.api.RoomDao;
import com.michal.dao.model.homestructure.Room;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class RoomDaoImpl implements RoomDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Room addNewRoom(Room room) {
        entityManager.persist(room);
        entityManager.flush();
        return room;
    }

    @Override
    @Transactional
    public List<Room> getAllRoom() {
        return entityManager.createQuery("from Room ").getResultList();
    }

    @Override
    @Transactional
    public Room get(Long id) {
        return entityManager.find(Room.class, id);
    }

    @Override
    @Transactional
    public boolean remove(Long id) {
        Room room = get(id);
        if (room != null) {
            entityManager.remove(room);
            return true;
        } else {
            return false;
        }
    }
}
