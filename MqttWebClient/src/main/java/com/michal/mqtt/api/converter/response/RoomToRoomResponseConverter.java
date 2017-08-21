package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.homestructure.Room;
import com.michal.mqtt.api.converter.ResponseConverter;
import com.michal.mqtt.api.homestructure.RoomsApi;
import com.michal.mqtt.api.homestructure.model.response.RoomResponseModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class RoomToRoomResponseConverter extends ResponseConverter<Room, RoomResponseModel> {

    @Override
    public RoomResponseModel convert(Room room) {
        RoomResponseModel roomResponseModel = new RoomResponseModel();
        if (room != null) {
            roomResponseModel.setRoom(room.getName());
            roomResponseModel.setDescription(room.getDescription());
            prepareLinks(room, roomResponseModel);
        }
        return roomResponseModel;
    }

    @Override
    protected void prepareLinks(Room room, RoomResponseModel roomResponseModel) {
        Link detail = linkTo(RoomsApi.class).slash(room.getId()).withSelfRel();
        roomResponseModel.add(detail);
    }
}
