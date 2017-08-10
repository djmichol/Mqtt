package com.michal.config;

import com.michal.dao.api.BrokerDao;
import com.michal.dao.api.DictionaryDefinitionDao;
import com.michal.dao.api.NodeDao;
import com.michal.dao.api.SensorDao;
import com.michal.mqtt.api.converter.request.BrokerModelToBrokerConverter;
import com.michal.mqtt.api.converter.request.DictionaryDefinitionRequestModelToDictionaryDefinitionConverter;
import com.michal.mqtt.api.converter.request.DictionaryValueModelToDictionaryValuesConverter;
import com.michal.mqtt.api.converter.request.GroovyRuleRequestToGroovyRuleConverter;
import com.michal.mqtt.api.converter.request.NodeRequestToBreokerNodeConverter;
import com.michal.mqtt.api.converter.request.RoomRequestModelToRoomConverter;
import com.michal.mqtt.api.converter.request.SensorRequestToNodeSensorConverter;
import com.michal.mqtt.api.converter.response.BrokerToClientModelConverter;
import com.michal.mqtt.api.converter.response.DictionaryToDictionaryResponseConverter;
import com.michal.mqtt.api.converter.response.GroovyRuleToGroovyRuleResponseConverter;
import com.michal.mqtt.api.converter.response.MqttClientToClientModelConverter;
import com.michal.mqtt.api.converter.response.NodeToNodeResponseConverter;
import com.michal.mqtt.api.converter.response.RecivedMessageToRecivedMessageResponseConverter;
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
    public SensorToSensorDetailsResponseConverter sensorToSensorDetailsResponseConverter(RoomToRoomResponseConverter roomToRoomResponseConverter) {
        return new SensorToSensorDetailsResponseConverter(roomToRoomResponseConverter);
    }

    @Bean
    public SensorToSensorResponseConverter sensorToSensorResponseConverter() {
        return new SensorToSensorResponseConverter();
    }

    @Bean
    public NodeToNodeResponseConverter brokerNodeToNodeResponseConverter(SensorToSensorDetailsResponseConverter sensorToSensorResponseConverter) {
        return new NodeToNodeResponseConverter(sensorToSensorResponseConverter);
    }

    @Bean
    public MqttClientToClientModelConverter mqttClientToClientModelConverter(NodeToNodeResponseConverter nodeToNodeResponseConverter) {
        return new MqttClientToClientModelConverter(nodeToNodeResponseConverter);
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
    public RoomToRoomDetailsResponseConverter roomToRoomDetailsResponseConverter(SensorToSensorResponseConverter sensorToSensorResponseConverter){
        return new RoomToRoomDetailsResponseConverter(sensorToSensorResponseConverter);
    }

    @Bean
    public RecivedMessageToRecivedMessageResponseConverter recivedMessageToRecivedMessageResponseConverter(){
        return new RecivedMessageToRecivedMessageResponseConverter();
    }

    @Bean
    public SendMessageToSendMessageResponseConverter sendMessageToSendMessageResponseConverter(){
        return new SendMessageToSendMessageResponseConverter();
    }

    @Bean
    public GroovyRuleToGroovyRuleResponseConverter groovyRuleToGroovyRuleResponseConverter(){
        return new GroovyRuleToGroovyRuleResponseConverter();
    }

    @Bean
    public GroovyRuleRequestToGroovyRuleConverter groovyRuleRequestToGroovyRuleConverter(SensorDao sensorDao){
        return new GroovyRuleRequestToGroovyRuleConverter(sensorDao);
    }
}
