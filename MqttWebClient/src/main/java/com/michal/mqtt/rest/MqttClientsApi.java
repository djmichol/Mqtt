package com.michal.mqtt.rest;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.app.Application;
import com.michal.dao.BrokerDao;
import com.michal.model.Broker;
import com.michal.mqtt.MqttClientImpl;

@RestController
@RequestMapping("/client")
public class MqttClientsApi {

	final static Logger logger = LogManager.getLogger(MqttClientsApi.class);
	
	@Autowired
	private Application application;
	@Autowired
	private BrokerDao brokerRepo;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MqttClientImpl>> loadClients() throws MqttException {
		return new ResponseEntity<List<MqttClientImpl>>(application.getBrokers(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewBroker(@RequestBody String brokerData) {
		JSONObject data = new JSONObject(brokerData);
		Broker broker = new Broker(data.getString("uri"), data.getString("user"), data.getString("password"));
		broker = brokerRepo.create(broker);
		try {
			application.getBrokers().add(new MqttClientImpl(broker, Application.CLIENT_ID));
		} catch (MqttException e) {
			logger.error(e);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/brokerId={brokerId}")
	public ResponseEntity<String> removeBroker(@PathVariable("brokerId") Long brokerId) {
		if (brokerRepo.removeBroker(brokerId)) {
			MqttClientImpl client = application.getByBrokerId(brokerId);
			application.getBrokers().remove(client);
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/brokerId={brokerId}/connect")
	public ResponseEntity<String> connectToBroker(@PathVariable("brokerId") Long brokerId) {
		if (application.getByBrokerId(brokerId).connect()) {
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/brokerId={brokerId}/disconnect")
	public ResponseEntity<String> disconnectToBroker(@PathVariable("brokerId") Long brokerId) {
		if (application.getByBrokerId(brokerId).disconnect()) {
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
