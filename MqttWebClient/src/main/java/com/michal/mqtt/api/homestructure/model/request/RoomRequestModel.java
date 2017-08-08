package com.michal.mqtt.api.homestructure.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "RoomRequestModel to add new room")
public class RoomRequestModel implements Serializable {

    @ApiModelProperty(value = "description")
    private String description;
    @ApiModelProperty(value = "room")
    @NotEmpty(message = "Room can't be empty!")
    private String room;
    @ApiModelProperty(value = "sensors")
    private List<String> sensors;

    public RoomRequestModel() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<String> getSensors() {
        return sensors;
    }

    public void setSensors(List<String> sensors) {
        this.sensors = sensors;
    }
}
