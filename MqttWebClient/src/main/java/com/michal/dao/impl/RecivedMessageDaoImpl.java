package com.michal.dao.impl;

import com.michal.dao.api.RecivedMessageDao;
import com.michal.dao.model.mqttdata.RecivedMessage;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RecivedMessageDaoImpl implements RecivedMessageDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public RecivedMessage create(RecivedMessage recivedMessage) {
        entityManager.persist(recivedMessage);
        entityManager.flush();
        return recivedMessage;
    }

    @Transactional
    @Override
    public List<RecivedMessage> getAll() {
        return entityManager.createQuery("Select message from RecivedMessage message").getResultList();
    }
}
