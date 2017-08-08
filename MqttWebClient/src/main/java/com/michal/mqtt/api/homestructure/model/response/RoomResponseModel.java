package com.michal.mqtt.api.homestructure.model.response;

import com.michal.dao.model.networkstructure.Sensor;
import com.michal.mqtt.api.networkstructure.model.request.SensorRequestModel;
import com.michal.mqtt.api.networkstructure.model.response.SensorResponseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
