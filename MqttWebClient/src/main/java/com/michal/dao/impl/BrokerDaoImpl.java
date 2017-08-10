package com.michal.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.michal.dao.api.BrokerDao;
import com.michal.dao.model.networkstructure.Broker;

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
		return entityManager.createQuery("Select broker from Broker broker").getResultList();
	}

	@Transactional
	@Override
	public Broker get(Long id) {
		return entityManager.find(Broker.class, id);
	}

	@Transactional
	@Override
	public boolean remove(Long id) {
		Broker broker = get(id);
		if(broker!=null){
			entityManager.remove(broker);
			return true;
		}else{
			return false;
		}
	}

	@Override
	@Transactional
	public void updateStatus(Long id, Date timestamp, String status) {
		Broker broker = get(id);
		if(broker!=null){
			broker.setStatus(status);
			broker.setStatusSince(timestamp);
			entityManager.merge(broker);
			entityManager.flush();
		}
	}

}
