package com.michal.mqtt.rest;

import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.dao.SensorDataDao;
import com.michal.model.SensorData;

@RestController
@RequestMapping("/sensorData")
public class SensorDataApi {

	@Autowired
	private SensorDataDao sensorDataRepo;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SensorData>> getAllData() throws MqttException {
		return new ResponseEntity<List<SensorData>>(sensorDataRepo.getAllData(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/topic={topic}")
	public ResponseEntity<List<SensorData>> getAllTopicData(@PathVariable("topic") String topic) throws MqttException {
		return new ResponseEntity<List<SensorData>>(sensorDataRepo.getDataByType(topic), HttpStatus.OK);
	}	
}
