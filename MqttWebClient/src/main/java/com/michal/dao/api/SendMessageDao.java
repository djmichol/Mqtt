package com.michal.dao.api;


import com.michal.dao.model.mqttdata.SendMessage;

import java.util.List;

public interface SendMessageDao {

    SendMessage create(SendMessage sendMessage);
    List<SendMessage> getAll();
}
