package com.michal.mqtt.api.networkstructure;


import com.michal.dao.api.SensorDao;
import com.michal.dao.model.networkstructure.Sensor;
import com.michal.mqtt.api.converter.request.SensorRequestToNodeSensorConverter;
import com.michal.mqtt.api.converter.response.SensorToSensorDetailsResponseConverter;
import com.michal.mqtt.api.networkstructure.model.request.SensorRequestModel;
import com.michal.mqtt.api.networkstructure.model.response.SensorResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorsApi {

    private SensorDao sensorDao;
    private SensorToSensorDetailsResponseConverter sensorToSensorResponseConverter;
    private SensorRequestToNodeSensorConverter sensorRequestToNodeSensorConverter;

    public SensorsApi(SensorDao sensorDao, SensorToSensorDetailsResponseConverter sensorToSensorResponseConverter, SensorRequestToNodeSensorConverter
            sensorRequestToNodeSensorConverter) {
        this.sensorDao = sensorDao;
        this.sensorToSensorResponseConverter = sensorToSensorResponseConverter;
        this.sensorRequestToNodeSensorConverter = sensorRequestToNodeSensorConverter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SensorResponseModel>> getAllSensors() {
        List<SensorResponseModel> sensorResponseModels = sensorToSensorResponseConverter.convert(sensorDao.getAllSensors());
        return new ResponseEntity<>(sensorResponseModels, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/room/{id}")
    public ResponseEntity<List<SensorResponseModel>> getSensorInRoom(@PathVariable("id") Long roomId) {
        List<SensorResponseModel> sensorResponseModels = sensorToSensorResponseConverter.convert(sensorDao.getByRoom(roomId));
        return new ResponseEntity<>(sensorResponseModels, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/node/{id}")
    public ResponseEntity<List<SensorResponseModel>> getSensorInNode(@PathVariable("id") Long nodeId) {
        List<SensorResponseModel> sensorResponseModels = sensorToSensorResponseConverter.convert(sensorDao.getByNode(nodeId));
        return new ResponseEntity<>(sensorResponseModels, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<SensorResponseModel> getSensorDetails(@PathVariable("id") Long sensorId) {
        SensorResponseModel sensorResponseModel = sensorToSensorResponseConverter.convert(sensorDao.get(sensorId));
        return new ResponseEntity<>(sensorResponseModel, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SensorResponseModel> addNewSensor(@Valid @RequestBody SensorRequestModel sensorRequestModel) {
        Sensor sensor = sensorDao.create(sensorRequestToNodeSensorConverter.convert(sensorRequestModel));
        return new ResponseEntity<>(sensorToSensorResponseConverter.convert(sensor), HttpStatus.OK);
    }
}