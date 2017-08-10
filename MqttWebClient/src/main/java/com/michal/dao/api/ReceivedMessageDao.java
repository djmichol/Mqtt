package com.michal.dao.api;

import com.michal.dao.model.mqttdata.ReceivedMessage;

import java.util.List;

public interface ReceivedMessageDao {
    ReceivedMessage create(ReceivedMessage receivedMessage);
    List<ReceivedMessage> getAll();
}
