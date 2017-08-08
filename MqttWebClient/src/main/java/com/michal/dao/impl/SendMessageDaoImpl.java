package com.michal.dao.impl;

import com.michal.dao.api.SendMessageDao;
import com.michal.dao.model.mqttdata.SendMessage;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SendMessageDaoImpl implements SendMessageDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public SendMessage create(SendMessage sendMessage) {
        entityManager.persist(sendMessage);
        entityManager.flush();
        return sendMessage;
    }

    @Transactional
    @Override
    public List<SendMessage> getAll() {
        return entityManager.createQuery("from SendMessage").getResultList();
    }

}
