package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.networkstructure.Sensor;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.networkstructure.model.response.SensorResponseModel;


public class SensorToSensorResponseConverter extends Converter<Sensor, SensorResponseModel> {

    @Override
    public SensorResponseModel convert(Sensor sensor) {
        if (sensor != null) {
            SensorResponseModel sensorResponseModel = new SensorResponseModel();
            sensorResponseModel.setLastSeen(sensor.getLastSeen());
            sensorResponseModel.setName(sensor.getName());
            sensorResponseModel.setNodeId(sensor.getNode().getId());
            sensorResponseModel.setType(sensor.getType());
            sensorResponseModel.setTopic(sensor.getTopic());
            return sensorResponseModel;
        }
        return null;
    }
}
