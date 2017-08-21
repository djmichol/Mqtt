package com.michal.mqtt.api.networkstructure.model.response;

import com.michal.mqtt.api.homestructure.model.response.RoomResponseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "Sensor response model")
public class SensorResponseModel extends ResourceSupport implements Serializable {

    @ApiModelProperty(value = "name")
    private String name;
    @ApiModelProperty(value = "node id")
    private Long nodeId;
    @ApiModelProperty(value = "node_name")
    private String nodeName;
    @ApiModelProperty(value = "type")
    private String type;
    @ApiModelProperty(value = "room")
    private RoomResponseModel room;
    @ApiModelProperty(value = "last seen")
    private Date lastSeen;
    @ApiModelProperty(value = "topic")
    private String topic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
