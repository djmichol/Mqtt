package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.homestructure.Room;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.homestructure.model.response.RoomResponseModel;

import java.util.stream.Collectors;

public class RoomToRoomDetailsResponseConverter extends Converter<Room,RoomResponseModel> {

    private SensorToSensorResponseConverter sensorToSensorResponseConverter;

    public RoomToRoomDetailsResponseConverter(SensorToSensorResponseConverter sensorToSensorResponseConverter) {
        this.sensorToSensorResponseConverter = sensorToSensorResponseConverter;
    }

    @Override
    public RoomResponseModel convert(Room room) {
        RoomResponseModel roomResponseModel = new RoomResponseModel();
        if(room !=null){
            roomResponseModel.setRoom(room.getName());
            roomResponseModel.setDescription(room.getDescription());
            roomResponseModel.setId(room.getId());
            roomResponseModel.setSensors(sensorToSensorResponseConverter.convert(room.getSensors().stream().collect(Collectors.toList())));
        }
        return roomResponseModel;
    }
}