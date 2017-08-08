package com.michal.mqtt.api.networkstructure.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(description = "Client response model")
public class ClientResponseModel implements Serializable{

    public ClientResponseModel() {
    }

    @ApiModelProperty(value = "brokerId")
    private Long brokerId;

    @ApiModelProperty(value = "brokerUrl")
    private String brokerUrl;

    @ApiModelProperty(value = "brokerUser")
    private String brokerUser;

    @ApiModelProperty(value = "nodes")
    private List<NodeResponseModel> nodes;

    @ApiModelProperty(value = "broker_name")
    private String name;

    @ApiModelProperty(value = "broker_status")
    private String status;

    @ApiModelProperty(value = "broker_status_since")
    private Date statusSince;

    public Long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Long brokerId) {
        this.brokerId = brokerId;
    }

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

    public List<NodeResponseModel> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeResponseModel> nodes) {
        this.nodes = nodes;
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
