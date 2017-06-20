package com.michal.mqtt.rest.model;

import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = 8166265136463374309L;
	
	private String topic;
	private String message;
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
