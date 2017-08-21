package com.michal.mqtt.api.homestructure.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

@ApiModel(description = "Room response model")
public class RoomResponseModel extends ResourceSupport implements Serializable  {

    @ApiModelProperty(value = "room")
    private String room;
    @ApiModelProperty(value = "description")
    private String description;

    public RoomResponseModel() {
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
