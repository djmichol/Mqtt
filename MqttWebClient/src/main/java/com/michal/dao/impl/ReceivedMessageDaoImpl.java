package com.michal.dao.impl;

import com.michal.dao.api.ReceivedMessageDao;
import com.michal.dao.model.mqttdata.ReceivedMessage;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ReceivedMessageDaoImpl implements ReceivedMessageDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public ReceivedMessage create(ReceivedMessage receivedMessage) {
        entityManager.persist(receivedMessage);
        entityManager.flush();
        return receivedMessage;
    }

    @Transactional
    @Override
    public List<ReceivedMessage> getAll() {
        return entityManager.createQuery("Select message from RecivedMessage message").getResultList();
    }
}
