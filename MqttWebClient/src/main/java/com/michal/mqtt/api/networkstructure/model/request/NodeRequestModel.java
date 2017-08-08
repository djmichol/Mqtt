package com.michal.mqtt.api.networkstructure.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
@ApiModel(description = "NodeRequestModel to add new node to broker")
public class NodeRequestModel implements Serializable{

    @ApiModelProperty(value = "name")
    @NotEmpty(message = "name can't be empty!")
    private String name;
    @ApiModelProperty(value = "brokerId")
    @NotNull(message = "brokerId can't be empty!")
    private Long brokerId;
    @ApiModelProperty(value = "url")
    @NotEmpty(message = "url can't be empty!")
    private String url;

    public NodeRequestModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Long brokerId) {
        this.brokerId = brokerId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
