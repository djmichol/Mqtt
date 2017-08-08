package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.networkstructure.Sensor;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.networkstructure.model.response.SensorResponseModel;


public class SensorToSensorDetailsResponseConverter extends Converter<Sensor, SensorResponseModel> {

    private RoomToRoomResponseConverter roomToRoomResponseConverter;

    public SensorToSensorDetailsResponseConverter(RoomToRoomResponseConverter roomToRoomResponseConverter) {
        this.roomToRoomResponseConverter = roomToRoomResponseConverter;
    }

    @Override
    public SensorResponseModel convert(Sensor sensor) {
        if (sensor != null) {
            SensorResponseModel sensorResponseModel = new SensorResponseModel();
            sensorResponseModel.setId(sensor.getId());
            sensorResponseModel.setLastSeen(sensor.getLastSeen());
            sensorResponseModel.setName(sensor.getName());
            sensorResponseModel.setNodeId(sensor.getNode().getId());
            sensorResponseModel.setNodeName(sensor.getNode().getName());
            sensorResponseModel.setRoom(roomToRoomResponseConverter.convert(sensor.getRoom()));
            sensorResponseModel.setType(sensor.getType());
            sensorResponseModel.setTopic(sensor.getTopic());
            return sensorResponseModel;
        }
        return null;
    }
}
