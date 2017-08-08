package com.michal.mqtt.api.mqtt;

import java.util.List;

import com.michal.mqtt.api.converter.response.SensorDataModelConverter;
import com.michal.mqtt.api.mqtt.model.response.SensorDataResponseModel;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.dao.api.SensorDataDao;

@RestController
@RequestMapping("/sensorsData")
public class SensorDataApi {

    private SensorDataDao sensorDataRepo;

    private SensorDataModelConverter sensorDataModelConverter;

    public SensorDataApi(SensorDataDao sensorDataRepo, SensorDataModelConverter sensorDataModelConverter) {
        this.sensorDataRepo = sensorDataRepo;
        this.sensorDataModelConverter = sensorDataModelConverter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SensorDataResponseModel>> getAllData() throws MqttException {
        List<SensorDataResponseModel> response = sensorDataModelConverter.convert(sensorDataRepo.getAllData());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{topic}")
    public ResponseEntity<List<SensorDataResponseModel>> getAllTopicData(@PathVariable("topic") String topic) throws MqttException {
        List<SensorDataResponseModel> response = sensorDataModelConverter.convert(sensorDataRepo.getDataByType(topic));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
