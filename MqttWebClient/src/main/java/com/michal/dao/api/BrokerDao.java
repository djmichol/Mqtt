package com.michal.dao.api;

import java.util.Date;
import java.util.List;

import com.michal.dao.model.networkstructure.Broker;

public interface BrokerDao {
	
	Broker create(Broker broker);
	List<Broker> getAllBrokers();
	Broker get(Long id);
	boolean remove(Long id);
	void updateStatus(Long id, Date timestamp, String status);
	
}
