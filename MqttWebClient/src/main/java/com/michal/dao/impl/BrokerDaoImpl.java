package com.michal.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.michal.dao.BrokerDao;
import com.michal.dao.model.Broker;

public class BrokerDaoImpl implements BrokerDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public Broker create(Broker broker) {
		entityManager.persist(broker);
		entityManager.flush();
		return broker;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Broker> getAllBrokers() {
		return entityManager.createQuery("from Broker").getResultList();
	}

	@Transactional
	@Override
	public Broker getById(Long id) {
		return entityManager.find(Broker.class, id);
	}

	@Transactional
	@Override
	public boolean removeBroker(Long id) {
		Broker broker = getById(id);
		if(broker!=null){
			entityManager.remove(broker);
			return true;
		}else{
			return false;
		}
	}

}
