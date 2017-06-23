package com.michal.dao;

import com.michal.dao.model.Topic;

public interface TopicDao {

	Topic createTopic(Topic topic);

	Topic getTopicById(Long id);

	Topic getTopicByNameAndBorkerId(String topic, Long brokerId);

	boolean removeTopic(Long id);
}
