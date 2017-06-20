package com.michal.mqtt.rest;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.dao.TopicDao;
import com.michal.dao.model.Topic;
import com.michal.mqtt.MqttApplication;
import com.michal.mqtt.MqttClientImpl;
import com.michal.mqtt.rest.model.TopicData;

@RestController
@RequestMapping("/client/topic")
public class TopicsApi {

    @Autowired
    private TopicDao topicRepo;
    @Autowired
    private MqttApplication mqttApplication;

    public TopicsApi(TopicDao topicRepo, MqttApplication mqttApplication) {
        this.topicRepo = topicRepo;
        this.mqttApplication = mqttApplication;
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<?> addTopicToBroker(@RequestBody TopicData topicData) {
        MqttClientImpl client = mqttApplication.getByBrokerId(topicData.getBrokerId());
        if (client != null) {
            Topic topic = topicRepo.createTopic(new Topic(topicData.getTopic(), client.getBroker()));
            client.getBroker().getTopics().add(topic);
            return new ResponseEntity<String>("Topic added", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("No MQTT client found", HttpStatus.PRECONDITION_FAILED);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "")
    public ResponseEntity<?> removeTopic(@RequestBody TopicData topicData) {
        MqttClientImpl client = mqttApplication.getByBrokerId(topicData.getBrokerId());
        if (client != null) {
            if (topicRepo.removeTopic(topicData.getTopicId())) {
                Topic toRemove = null;
                for (Topic topic : client.getBroker().getTopics()) {
                    if (topic.getTopicId().equals(topicData.getTopicId())) {
                        toRemove = topic;
                    }
                }
                if (toRemove != null) {
                    client.getBroker().getTopics().remove(toRemove);
                }
                return new ResponseEntity<String>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cannot find topic to remove", HttpStatus.PRECONDITION_FAILED);
            }
        }
        return new ResponseEntity<>("No MQTT client found", HttpStatus.PRECONDITION_FAILED);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/subscribe/{brokerId}/{topicId}")
    public ResponseEntity<String> subscribeTopic(@PathVariable("brokerId") Long brokerId, @PathVariable("topicId") Long topicId) throws MqttException {
        if(changeTopicSubStatus(brokerId,topicId)){
            return new ResponseEntity<String>("Topic: " + topicId + " subscribed", HttpStatus.OK);
        }
        return new ResponseEntity<>("Cannot subscribe topic", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/unsubscribe/{brokerId}/{topicId}")
    public ResponseEntity<String> unsubscribeTopic(@PathVariable("brokerId") Long brokerId, @PathVariable("topicId") Long topicId) throws MqttException {
        if(changeTopicSubStatus(brokerId,topicId)){
            return new ResponseEntity<String>("Topic: " + topicId + " unsubscribed", HttpStatus.OK);
        }
        return new ResponseEntity<>("Cannot unsubscribe topic", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean changeTopicSubStatus(Long brokerId, Long topicId) throws MqttException {
        MqttClientImpl client = mqttApplication.getByBrokerId(brokerId);
        if (client != null) {
            for (Topic topic : client.getBroker().getTopics()) {
                if (topic.getTopicId().equals(topicId)) {
                    if (topic.isSubscribed()) {
                        if (client.unsubscribeTopic(topic.getTopic())) {
                            topic.setSubscribed(false);
                            return true;
                        }
                    } else {
                        if (client.subscribeTopic(topic.getTopic())) {
                            topic.setSubscribed(true);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
