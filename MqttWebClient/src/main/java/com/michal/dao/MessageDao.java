package com.michal.dao;

import java.util.List;

import com.michal.model.Message;

public interface MessageDao {

	void create(Message message);

	List<Message> getAllMessages();

	List<Message> getAllMessagesForTopic(String topic);

}
