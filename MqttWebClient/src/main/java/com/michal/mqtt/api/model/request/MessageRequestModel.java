package com.michal.mqtt.api.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(description = "MessageRequestModel to send to chosen topic by chosen broker")
public class MessageRequestModel implements Serializable{

	private static final long serialVersionUID = 8166265136463374309L;

	@NotEmpty(message = "Topic can't be empty!")
	@ApiModelProperty(value = "topic", allowableValues = "home/kitchen/temperature", required = true)
	private String topic;
	@NotEmpty(message = "MessageRequestModel can't be empty!")
	@ApiModelProperty(value = "message", allowableValues = "message", required = true)
	private String message;
	@NotNull(message = "Broker can't be null!")
	@ApiModelProperty(value = "brokerId", allowableValues = "1", required = true)
	private Long brokerId;
	
	public MessageRequestModel(){}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(Long brokerId) {
		this.brokerId = brokerId;
	};
	
	

}
