package com.michal.mqtt.rest.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = 8166265136463374309L;

	@NotEmpty(message = "Topic can't be empty!")
	private String topic;
	@NotEmpty(message = "Message can't be empty!")
	private String message;
	@NotNull(message = "Broker can't be null!")
	private Long brokerId;
	
	public Message(){}

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
