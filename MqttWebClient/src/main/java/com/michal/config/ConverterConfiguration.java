package com.michal.config;

import com.michal.dao.api.ActionDao;
import com.michal.dao.api.BrokerDao;
import com.michal.dao.api.DictionaryDefinitionDao;
import com.michal.dao.api.NodeDao;
import com.michal.dao.api.SensorDao;
import com.michal.mqtt.api.converter.request.ActionRequestToActionConverter;
import com.michal.mqtt.api.converter.request.BrokerModelToBrokerConverter;
import com.michal.mqtt.api.converter.request.DictionaryDefinitionRequestModelToDictionaryDefinitionConverter;
import com.michal.mqtt.api.converter.request.DictionaryValueModelToDictionaryValuesConverter;
import com.michal.mqtt.api.converter.request.GroovyRuleRequestToGroovyRuleConverter;
import com.michal.mqtt.api.converter.request.NodeRequestToBreokerNodeConverter;
import com.michal.mqtt.api.converter.request.RoomRequestModelToRoomConverter;
import com.michal.mqtt.api.converter.request.SensorRequestToNodeSensorConverter;
import com.michal.mqtt.api.converter.response.ActionToActionResponseConverter;
import com.michal.mqtt.api.converter.response.BrokerToClientModelConverter;
import com.michal.mqtt.api.converter.response.DictionaryToDictionaryResponseConverter;
import com.michal.mqtt.api.converter.response.GroovyRuleToGroovyRuleResponseConverter;
import com.michal.mqtt.api.converter.response.MqttClientToClientModelConverter;
import com.michal.mqtt.api.converter.response.NodeToNodeResponseConverter;
import com.michal.mqtt.api.converter.response.ReceivedMessageToReceivedMessageResponseConverter;
import com.michal.mqtt.api.converter.response.RoomToRoomDetailsResponseConverter;
import com.michal.mqtt.api.converter.response.RoomToRoomResponseConverter;
import com.michal.mqtt.api.converter.response.SendMessageToSendMessageResponseConverter;
import com.michal.mqtt.api.converter.response.SensorDataModelConverter;
import com.michal.mqtt.api.converter.response.SensorToSensorDetailsResponseConverter;
import com.michal.mqtt.api.converter.response.SensorToSensorResponseConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfiguration {

    @Bean
    public SensorDataModelConverter sensorDataModelConverter() {
        return new SensorDataModelConverter();
    }

    @Bean
    public SensorToSensorDetailsResponseConverter sensorToSensorDetailsResponseConverter() {
        return new SensorToSensorDetailsResponseConverter();
    }

    @Bean
    public SensorToSensorResponseConverter sensorToSensorResponseConverter() {
        return new SensorToSensorResponseConverter();
    }

    @Bean
    public NodeToNodeResponseConverter brokerNodeToNodeResponseConverter() {
        return new NodeToNodeResponseConverter();
    }

    @Bean
    public MqttClientToClientModelConverter mqttClientToClientModelConverter() {
        return new MqttClientToClientModelConverter();
    }

    @Bean
    public BrokerModelToBrokerConverter brokerModelToBrokerConverter() {
        return new BrokerModelToBrokerConverter();
    }

    @Bean
    public RoomToRoomResponseConverter placeToPlaceResponseModelConverter() {
        return new RoomToRoomResponseConverter();
    }

    @Bean
    public RoomRequestModelToRoomConverter placeRequestModelToPlaceConverter(SensorDao sensorDao) {
        return new RoomRequestModelToRoomConverter(sensorDao);
    }

    @Bean
    public DictionaryToDictionaryResponseConverter dictionaryToDictionaryResponseModelConverter() {
        return new DictionaryToDictionaryResponseConverter();
    }

    @Bean
    public DictionaryDefinitionRequestModelToDictionaryDefinitionConverter dictionaryDefinitionRequestModelToDictionaryDefinitionConverter() {
        return new DictionaryDefinitionRequestModelToDictionaryDefinitionConverter();
    }

    @Bean
    public DictionaryValueModelToDictionaryValuesConverter dictionaryValueModelToDictionaryValuesConverter(DictionaryDefinitionDao dictionaryDefinitionDao) {
        return new DictionaryValueModelToDictionaryValuesConverter(dictionaryDefinitionDao);
    }

    @Bean
    public NodeRequestToBreokerNodeConverter nodeRequestToBreokerNodeConverter(BrokerDao brokerDao) {
        return new NodeRequestToBreokerNodeConverter(brokerDao);
    }

    @Bean
    public SensorRequestToNodeSensorConverter sensorRequestToNodeSensorConverter(NodeDao nodeDao) {
        return new SensorRequestToNodeSensorConverter(nodeDao);
    }

    @Bean
    public BrokerToClientModelConverter brokerToClientModelConverter(NodeToNodeResponseConverter nodeToNodeResponseConverter){
        return new BrokerToClientModelConverter(nodeToNodeResponseConverter);
    }

    @Bean
    public RoomToRoomDetailsResponseConverter roomToRoomDetailsResponseConverter(){
        return new RoomToRoomDetailsResponseConverter();
    }

    @Bean
    public ReceivedMessageToReceivedMessageResponseConverter recivedMessageToRecivedMessageResponseConverter(){
        return new ReceivedMessageToReceivedMessageResponseConverter();
    }

    @Bean
    public SendMessageToSendMessageResponseConverter sendMessageToSendMessageResponseConverter(){
        return new SendMessageToSendMessageResponseConverter();
    }

    @Bean
    public GroovyRuleToGroovyRuleResponseConverter groovyRuleToGroovyRuleResponseConverter(ActionToActionResponseConverter actionToActionResponseConverter){
        return new GroovyRuleToGroovyRuleResponseConverter(actionToActionResponseConverter);
    }

    @Bean
    public GroovyRuleRequestToGroovyRuleConverter groovyRuleRequestToGroovyRuleConverter(SensorDao sensorDao, ActionDao actionDao){
        return new GroovyRuleRequestToGroovyRuleConverter(sensorDao, actionDao);
    }

    @Bean
    public ActionToActionResponseConverter actionToActionResponseConverter(){
        return new ActionToActionResponseConverter();
    }

    @Bean
    public ActionRequestToActionConverter actionRequestToActionConverter(){
        return new ActionRequestToActionConverter();
    }
}
