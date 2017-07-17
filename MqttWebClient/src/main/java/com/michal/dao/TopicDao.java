package com.michal.dao;

import com.michal.dao.model.Topic;
import com.michal.mqtt.callback.topic.CallbackEnum;

public interface TopicDao {

	Topic createTopic(Topic topic);

	Topic getTopicById(Long id);

	Topic getTopicForBrokerByTopicAndCallback(String topic, Long brokerId, CallbackEnum callback);

	boolean removeTopic(Long id);
}
