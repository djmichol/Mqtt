package com.michal.dao;

import com.michal.model.Topic;

public interface TopicDao {

	Topic createTopic(Topic topic);

	Topic getTopicById(Long id);

	boolean removeTopic(Long id);
}
