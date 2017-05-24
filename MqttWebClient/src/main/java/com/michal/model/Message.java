package com.michal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mqttMessage")
public class Message implements Serializable {

	private static final long serialVersionUID = 6586347452739642461L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "message_id")
	private Long messageId;
	@Column(name = "message_topic", nullable = false)
	private String messageTopic;
	@Column(name = "message_payload", nullable = false)
	private String messagePayload;

	public Message() {}
	
	public Message(String messageTopic, String messagePayload){
		this.messageTopic = messageTopic;
		this.messagePayload = messagePayload;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getMessageTopic() {
		return messageTopic;
	}

	public void setMessageTopic(String messageTopic) {
		this.messageTopic = messageTopic;
	}

	public String getMessagePayload() {
		return messagePayload;
	}

	public void setMessagePayload(String messagePayload) {
		this.messagePayload = messagePayload;
	}

}
