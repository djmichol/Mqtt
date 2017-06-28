package com.michal.dao.impl;

import com.michal.dao.NotificationDao;
import com.michal.dao.model.Notification;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class NotificationDaoImpl implements NotificationDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Notification create(Notification data) {
        entityManager.persist(data);
        entityManager.flush();
        return data;
    }

    @Override
    @Transactional
    public void readNotification(Long notificationId) {
        Notification data = entityManager.find(Notification.class, notificationId);
        if (data != null) {
            data.setRead(true);
            entityManager.merge(data);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Notification> getAllNewNotifications() {
        Query query = entityManager.createQuery("Select data from Notification data where data.read = :read", Notification.class);
        query.setParameter("read", 0);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Notification> getAllNotifications(String topic) {
        return entityManager.createQuery("from Notification").getResultList();
    }
}

