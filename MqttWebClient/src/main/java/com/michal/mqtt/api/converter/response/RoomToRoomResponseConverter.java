package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.homestructure.Room;
import com.michal.mqtt.api.homestructure.model.response.RoomResponseModel;
import com.michal.mqtt.api.converter.Converter;

public class RoomToRoomResponseConverter extends Converter<Room,RoomResponseModel> {

    @Override
    public RoomResponseModel convert(Room room) {
        RoomResponseModel roomResponseModel = new RoomResponseModel();
        if(room !=null){
            roomResponseModel.setRoom(room.getName());
            roomResponseModel.setDescription(room.getDescription());
            roomResponseModel.setId(room.getId());
        }
        return roomResponseModel;
    }
}
