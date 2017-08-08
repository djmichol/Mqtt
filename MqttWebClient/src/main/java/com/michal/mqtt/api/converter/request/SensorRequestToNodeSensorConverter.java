package com.michal.mqtt.api.converter.request;

import com.michal.dao.api.NodeDao;
import com.michal.dao.model.networkstructure.Sensor;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.networkstructure.model.request.SensorRequestModel;

public class SensorRequestToNodeSensorConverter extends Converter<SensorRequestModel, Sensor>{

    private NodeDao nodeDao;

    public SensorRequestToNodeSensorConverter(NodeDao nodeDao) {
        this.nodeDao = nodeDao;
    }

    @Override
    public Sensor convert(SensorRequestModel sensorRequestModel) {
        Sensor sensor = new Sensor();
        sensor.setNode(nodeDao.get(sensorRequestModel.getNodeId()));
        sensor.setName(sensorRequestModel.getName());
        sensor.setType(sensorRequestModel.getType());
        sensor.setTopic(sensorRequestModel.getTopic());
        return sensor;
    }
}
