package com.michal.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.michal.dao.TopicDao;
import com.michal.dao.model.Topic;
import org.springframework.transaction.event.TransactionalEventListener;

public class TopicDaoImpl implements TopicDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Topic createTopic(Topic topic) {
        entityManager.persist(topic);
        entityManager.flush();
        return topic;
    }

    @Override
    @Transactional
    public Topic getTopicById(Long id) {
        return entityManager.find(Topic.class, id);
    }

    @Override
    @Transactional
    public Topic getTopicByNameAndBorkerId(String topic, Long brokerId) {
        Query query = entityManager.createQuery("Select topic from Topic topic where topic.topic = :topic and topic.broker.id=:brokerId", Topic.class);
        query.setParameter("topic", topic);
        query.setParameter("brokerId", brokerId);
        return (Topic) query.getSingleResult();
    }


    @Override
    @Transactional
    public boolean removeTopic(Long id) {
        Topic topic = getTopicById(id);
        if (topic != null) {
            entityManager.remove(topic);
            entityManager.flush();
            return true;
        } else {
            return false;
        }
    }

}
