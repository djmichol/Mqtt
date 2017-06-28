package com.michal.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.michal.mqtt.callback.topic.CallbackEnum;

@Entity
@Table(name = "mqttTopic")
public class Topic implements Serializable{

	private static final long serialVersionUID = 6586347452769642461L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "topic_id")
	private Long topicId;
	
	@Column(name = "topic_name", nullable = false)
	private String topic;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mqtt_broker_id", nullable = false)
	private Broker broker;

	@Column(name = "topic_callback", nullable = false)
	@Enumerated(EnumType.STRING)
	private CallbackEnum callbackEnum;

	@Transient
	private boolean subscribed = false;
	
	public Topic(){}
	
	public Topic(String topic, Broker broker, CallbackEnum callbackEnum){
		this.topic = topic;
		this.broker = broker;
		this.callbackEnum = callbackEnum;
	}
	
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Broker getBroker() {
		return broker;
	}
	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}

	public CallbackEnum getCallbackEnum() {
		return callbackEnum;
	}

	public void setCallbackEnum(CallbackEnum callbackEnum) {
		this.callbackEnum = callbackEnum;
	}
}
