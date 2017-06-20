package com.michal.dao;

import com.michal.dao.model.Topic;

public interface TopicDao {

	Topic createTopic(Topic topic);

	Topic getTopicById(Long id);

	boolean removeTopic(Long id);
}
