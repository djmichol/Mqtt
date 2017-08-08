package com.michal.dao.api;

import com.michal.dao.model.mqttdata.RecivedMessage;

import java.util.List;

public interface RecivedMessageDao {
    RecivedMessage create(RecivedMessage recivedMessage);
    List<RecivedMessage> getAll();
}
