package com.michal.dao;

import java.util.List;

import com.michal.model.Broker;

public interface BrokerDao {
	
	Broker create(Broker broker);
	List<Broker> getAllBrokers();
	Broker getById(Long id);
	Broker getByURI(String uri);
	boolean removeBroker(Long id);
	
}
