package com.michal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.michal.model.Broker;

@Repository
public class BrokerDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;

	public void create(Broker broker) {
		entityManager.persist(broker);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Broker> getAll() {
		return entityManager.createQuery("from Broker").getResultList();
	}

	public Broker getById(String id) {
		return entityManager.find(Broker.class, id);
	}

}
