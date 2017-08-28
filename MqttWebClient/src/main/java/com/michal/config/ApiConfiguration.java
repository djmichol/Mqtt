package com.michal.config;

import com.michal.dao.api.ActionDao;
import com.michal.dao.api.BrokerDao;
import com.michal.dao.api.DictionaryDefinitionDao;
import com.michal.dao.api.DictionaryValuesDao;
import com.michal.dao.api.NodeDao;
import com.michal.dao.api.ReceivedMessageDao;
import com.michal.dao.api.RoomDao;
import com.michal.dao.api.SendMessageDao;
import com.michal.dao.api.SensorDao;
import com.michal.dao.api.SensorDataDao;
import com.michal.mqtt.MqttApplication;
import com.michal.mqtt.api.converter.request.ActionRequestToActionConverter;
import com.michal.mqtt.api.converter.request.BrokerModelToBrokerConverter;
import com.michal.mqtt.api.converter.request.DictionaryDefinitionRequestModelToDictionaryDefinitionConverter;
import com.michal.mqtt.api.converter.request.DictionaryValueModelToDictionaryValuesConverter;
import com.michal.mqtt.api.converter.request.NodeRequestToBreokerNodeConverter;
import com.michal.mqtt.api.converter.request.RoomRequestModelToRoomConverter;
import com.michal.mqtt.api.converter.response.ActionToActionResponseConverter;
import com.michal.mqtt.api.converter.response.BrokerToClientModelConverter;
import com.michal.mqtt.api.converter.response.DictionaryToDictionaryResponseConverter;
import com.michal.mqtt.api.converter.response.NodeToNodeResponseConverter;
import com.michal.mqtt.api.converter.response.ReceivedMessageToReceivedMessageResponseConverter;
import com.michal.mqtt.api.converter.response.RoomToRoomDetailsResponseConverter;
import com.michal.mqtt.api.converter.response.SendMessageToSendMessageResponseConverter;
import com.michal.mqtt.api.converter.response.SensorDataModelConverter;
import com.michal.mqtt.api.groovyrule.ActionApi;
import com.michal.mqtt.api.mqtt.MessageApi;
import com.michal.mqtt.api.homestructure.RoomsApi;
import com.michal.mqtt.api.mqtt.SensorDataApi;
import com.michal.mqtt.api.auth.AuthApi;
import com.michal.mqtt.api.dictionary.DictionaryApi;
import com.michal.mqtt.api.networkstructure.ClientsApi;
import com.michal.mqtt.api.networkstructure.NodesApi;
import com.michal.mqtt.api.report.ReportApi;
import com.michal.mqtt.api.utils.RestCallService;
import com.michal.mqtt.engine.client.ReceivedMessageExtractor;
import com.michal.mqtt.engine.raport.ReportEngine;
import com.michal.mqtt.engine.raport.fileGenerator.ReportGenerator;
import com.michal.mqtt.error.ExceptionController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@Configuration
public class ApiConfiguration {

    @Bean
    @Scope("singleton")
    public MqttApplication mqttApplication(BrokerDao brokerRepo, SendMessageDao sendMessageDao, ReceivedMessageDao receivedMessageDao, ReceivedMessageExtractor receivedMessageExtractor) {
        return new MqttApplication(brokerRepo, sendMessageDao, receivedMessageDao, receivedMessageExtractor);
    }

    @Bean
    public MessageApi messageApi(MqttApplication mqttApplication, ReceivedMessageDao receivedMessageDao, ReceivedMessageToReceivedMessageResponseConverter receivedMessageToReceivedMessageResponseConverter, SendMessageToSendMessageResponseConverter sendMessageToSendMessageResponseConverter, SendMessageDao sendMessageDao) {
        return new MessageApi(mqttApplication, receivedMessageToReceivedMessageResponseConverter, sendMessageToSendMessageResponseConverter, receivedMessageDao, sendMessageDao);
    }

    @Bean
    public SensorDataApi sensorDataApi(SensorDataDao sensorDataDao, SensorDataModelConverter sensorDataModelConverter) {
        return new SensorDataApi(sensorDataDao, sensorDataModelConverter);
    }

    @Bean
    public ClientsApi clientsApi(MqttApplication mqttApplication, BrokerDao brokerRepo, BrokerToClientModelConverter brokerToClientModelConverter, BrokerModelToBrokerConverter
            brokerModelToBrokerConverter, SendMessageDao sendMessageDao, ReceivedMessageDao receivedMessageDao, ReceivedMessageExtractor receivedMessageExtractor) {
        return new ClientsApi(mqttApplication, brokerRepo, brokerToClientModelConverter, brokerModelToBrokerConverter, sendMessageDao, receivedMessageDao, receivedMessageExtractor);
    }

    @Bean
    public ExceptionController exceptionController() {
        return new ExceptionController();
    }

    @Bean
    public RoomsApi roomsApi(RoomDao roomDao, RoomToRoomDetailsResponseConverter roomToRoomResponseConverter, RoomRequestModelToRoomConverter roomRequestModelToRoomConverter,
                             SensorDao sensorDao) {
        return new RoomsApi(roomDao, roomToRoomResponseConverter, roomRequestModelToRoomConverter, sensorDao);
    }

    @Bean
    public DictionaryApi dictionaryApi(DictionaryValuesDao dictionaryValuesDao, DictionaryDefinitionDao dictionaryDefinitionDao, DictionaryToDictionaryResponseConverter
            dictionaryToDictionaryResponseConverter, DictionaryDefinitionRequestModelToDictionaryDefinitionConverter
            dictionaryDefinitionRequestModelToDictionaryDefinitionConverter, DictionaryValueModelToDictionaryValuesConverter dictionaryValueModelToDictionaryValuesConverter) {
        return new DictionaryApi(dictionaryValuesDao, dictionaryDefinitionDao, dictionaryToDictionaryResponseConverter,
                dictionaryDefinitionRequestModelToDictionaryDefinitionConverter, dictionaryValueModelToDictionaryValuesConverter);
    }

    @Bean
    public AuthApi authApi(RestCallService restCallService) {
        return new AuthApi(restCallService);
    }

    @Bean
    public RestCallService restCallService(Environment environment) {
        return new RestCallService(environment);
    }

    @Bean
    public NodesApi nodesApi(NodeDao nodeDao, NodeToNodeResponseConverter nodeToNodeResponseConverter, NodeRequestToBreokerNodeConverter nodeRequestToBreokerNodeConverter) {
        return new NodesApi(nodeDao, nodeToNodeResponseConverter, nodeRequestToBreokerNodeConverter);
    }

    @Bean
    public ActionApi actionApi(ActionDao actionDao, ActionToActionResponseConverter actionToActionResponseConverter, ActionRequestToActionConverter
            actionRequestToActionConverter) {
        return new ActionApi(actionDao, actionToActionResponseConverter, actionRequestToActionConverter);
    }

    @Bean
    public ReportApi reportApi(ReportEngine reportEngine, ReportGenerator reportGenerator){
        return new ReportApi(reportEngine, reportGenerator);
    }
}
