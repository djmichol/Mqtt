package com.michal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.michal.model.Broker;

@Repository
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
	public Broker getByURI(String uri) {
		 Query query = entityManager.createQuery("Select b from mqttBrokers b where b.mqtt_uri = :uri", Broker.class);
		 query.setParameter("uri", uri);
		 return (Broker) query.getSingleResult();
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
