package com.michal.mqtt.rest;

import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.dao.SensorDataDao;
import com.michal.dao.model.SensorData;

@RestController
@RequestMapping("/sensorData")
public class SensorDataApi {

	private SensorDataDao sensorDataRepo;

	public SensorDataApi(SensorDataDao sensorDataRepo){
		this.sensorDataRepo = sensorDataRepo;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SensorData>> getAllData() throws MqttException {
		return new ResponseEntity<>(sensorDataRepo.getAllData(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{topic}")
	public ResponseEntity<List<SensorData>> getAllTopicData(@PathVariable("topic") String topic) throws MqttException {
		return new ResponseEntity<>(sensorDataRepo.getDataByType(topic), HttpStatus.OK);
	}	
}
