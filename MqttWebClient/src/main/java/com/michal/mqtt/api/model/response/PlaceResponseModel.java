package com.michal.mqtt.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "Place response model")
public class PlaceResponseModel implements Serializable {

    @ApiModelProperty(value = "place")
    private String place;
    @ApiModelProperty(value = "room")
    private String room;

    public PlaceResponseModel() {
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
