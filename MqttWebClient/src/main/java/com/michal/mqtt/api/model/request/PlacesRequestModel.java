package com.michal.mqtt.api.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ApiModel(description = "PlacesRequestModel to add new place")
public class PlacesRequestModel implements Serializable{

    @ApiModelProperty(value = "place")
    @NotEmpty(message = "Place can't be empty!")
    private String place;
    @ApiModelProperty(value = "room")
    private String room;

    public PlacesRequestModel() {
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
