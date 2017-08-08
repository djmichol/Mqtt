package com.michal.mqtt.api.converter.request;

import com.michal.dao.api.SensorDao;
import com.michal.dao.model.homestructure.Room;
import com.michal.dao.model.networkstructure.Sensor;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.homestructure.model.request.RoomRequestModel;

import java.util.HashSet;
import java.util.Set;

public class RoomRequestModelToRoomConverter extends Converter<RoomRequestModel, Room> {

    private SensorDao sensorDao;

    public RoomRequestModelToRoomConverter(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }

    @Override
    public Room convert(RoomRequestModel roomRequestModel) {
        Room room = new Room();
        if (roomRequestModel != null) {
            room.setName(roomRequestModel.getRoom());
            room.setDescription(roomRequestModel.getDescription());
            Set<Sensor> sensors = new HashSet<>();
            roomRequestModel.getSensors().forEach(sensorId -> sensors.add(sensorDao.get(Long.decode(sensorId))));
            room.setSensors(sensors);
        }
        return room;
    }
}
