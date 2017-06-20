package com.michal.mqtt.rest.model;

import java.io.Serializable;

public class TopicData implements Serializable {

	public TopicData(){}
	
	private Long brokerId;
	private Long topicId;
	private String topic;
	public Long getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(Long brokerId) {
		this.brokerId = brokerId;
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
	
}
