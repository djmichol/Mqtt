package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.mqttdata.SensorData;
import com.michal.mqtt.api.converter.ResponseConverter;
import com.michal.mqtt.api.mqtt.model.response.SensorDataResponseModel;
import com.michal.mqtt.api.networkstructure.SensorsApi;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class SensorDataModelConverter extends ResponseConverter<SensorData, SensorDataResponseModel> {

    @Override
    public SensorDataResponseModel convert(SensorData sensorData) {
        SensorDataResponseModel sensorDataModel = new SensorDataResponseModel();
        sensorDataModel.setDataTimestamp(sensorData.getTimestamp());
        sensorDataModel.setDataType(sensorData.getType());
        sensorDataModel.setSensorData(sensorData.getData());
        prepareLinks(sensorData, sensorDataModel);
        return sensorDataModel;
    }

    @Override
    protected void prepareLinks(SensorData sensorData, SensorDataResponseModel sensorDataModel) {
        Link sensor = linkTo(methodOn(SensorsApi.class).getSensorDetails(sensorData.getSensor().getId())).withRel("sensorData.sensor");
        sensorDataModel.add(sensor);
    }
}
