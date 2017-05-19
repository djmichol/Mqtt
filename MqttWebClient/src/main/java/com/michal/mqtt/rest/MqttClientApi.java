package com.michal.mqtt.rest;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.dao.BrokerDaoImpl;
import com.michal.model.Broker;

@RestController
@RequestMapping("/mqtt")
public class MqttClientApi {

	@Autowired
	private BrokerDaoImpl repository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewBroker(String brokerData) {
		JSONObject data = new JSONObject(brokerData);
		Broker broker = new Broker(data.getString("uri"), data.getString("user"), data.getString("password"));
		repository.create(broker);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Broker>> getBrokers(){
		List<Broker> brokers = repository.getAll();
		return new ResponseEntity<List<Broker>>(brokers,HttpStatus.OK);
	}

}
