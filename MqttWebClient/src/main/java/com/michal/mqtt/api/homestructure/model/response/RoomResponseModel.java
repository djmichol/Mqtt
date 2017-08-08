package com.michal.mqtt.api.homestructure.model.response;

import com.michal.mqtt.api.networkstructure.model.response.SensorResponseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "Room response model")
public class RoomResponseModel implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "room")
    private String room;
    @ApiModelProperty(value = "description")
    private String description;
    @ApiModelProperty(value = "sensors")
    private List<SensorResponseModel> sensors;

    public RoomResponseModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<SensorResponseModel> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorResponseModel> sensors) {
        this.sensors = sensors;
    }
}
