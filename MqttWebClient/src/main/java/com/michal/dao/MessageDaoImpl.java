package com.michal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.michal.model.Message;

@Repository
public class MessageDaoImpl implements MessageDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void create(Message message) {
		entityManager.persist(message);
		return;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Message> getAllMessages() {
		return entityManager.createQuery("from Message").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Message> getAllMessagesForTopic(String topic) {
		 Query query = entityManager.createQuery("Select m from mqttMessage m where b.message_topic = :topic", Message.class);
		 query.setParameter("topic", topic);
		 return query.getResultList();
	}

}
