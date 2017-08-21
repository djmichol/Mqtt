package com.michal.mqtt.api.networkstructure.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "Client response model")
public class ClientResponseModel extends ResourceSupport implements Serializable {

    public ClientResponseModel() {
    }

    @ApiModelProperty(value = "brokerUrl")
    private String brokerUrl;

    @ApiModelProperty(value = "brokerUser")
    private String brokerUser;

    @ApiModelProperty(value = "broker_name")
    private String name;

    @ApiModelProperty(value = "broker_status")
    private String status;

    @ApiModelProperty(value = "broker_status_since")
    private Date statusSince;

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public String getBrokerUser() {
        return brokerUser;
    }

    public void setBrokerUser(String brokerUser) {
        this.brokerUser = brokerUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatusSince() {
        return statusSince;
    }

    public void setStatusSince(Date statusSince) {
        this.statusSince = statusSince;
    }
}
