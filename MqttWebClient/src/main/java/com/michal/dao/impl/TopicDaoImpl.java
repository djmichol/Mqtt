package com.michal.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.michal.dao.TopicDao;
import com.michal.dao.model.Topic;

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
