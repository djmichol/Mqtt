package com.michal.mqtt.rest;

import java.util.List;

import com.michal.mqtt.rest.converter.SensorDataModelConverter;
import com.michal.mqtt.rest.model.SensorDataModel;
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

	private SensorDataModelConverter sensorDataModelConverter;

	public SensorDataApi(SensorDataDao sensorDataRepo, SensorDataModelConverter sensorDataModelConverter){
		this.sensorDataRepo = sensorDataRepo;
		this.sensorDataModelConverter = sensorDataModelConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SensorDataModel>> getAllData() throws MqttException {
		List<SensorDataModel> response = sensorDataModelConverter.convert(sensorDataRepo.getAllData());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{topic}")
	public ResponseEntity<List<SensorDataModel>> getAllTopicData(@PathVariable("topic") String topic) throws MqttException {
		List<SensorDataModel> response = sensorDataModelConverter.convert(sensorDataRepo.getDataByType(topic));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}	
}
