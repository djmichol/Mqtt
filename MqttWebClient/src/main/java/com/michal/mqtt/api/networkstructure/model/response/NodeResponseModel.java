package com.michal.mqtt.api.networkstructure.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(description = "Node response model")
public class NodeResponseModel extends ResourceSupport implements Serializable {
    public NodeResponseModel(){}

    @ApiModelProperty(value = "name")
    private String name;
    @ApiModelProperty(value = "url")
    private String url;
    @ApiModelProperty(value = "status")
    private String status;
    @ApiModelProperty(value = "last seen")
    private Date lastSeen;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }
}
