package com.michal.mqtt.api;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.dao.TopicDao;
import com.michal.dao.model.Topic;
import com.michal.mqtt.MqttApplication;
import com.michal.mqtt.MqttClientImpl;
import com.michal.mqtt.api.model.request.TopicRequestModel;

import javax.validation.Valid;

@RestController
@RequestMapping("/client/topic")
public class TopicsApi {

    private TopicDao topicRepo;
    private MqttApplication mqttApplication;

    public TopicsApi(TopicDao topicRepo, MqttApplication mqttApplication) {
        this.topicRepo = topicRepo;
        this.mqttApplication = mqttApplication;
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<String> addTopicToBroker(@Valid @RequestBody TopicRequestModel topicRequestModel) {
        MqttClientImpl client = mqttApplication.getByBrokerId(topicRequestModel.getBrokerId());
        if (client != null) {
            Topic topic = topicRepo.createTopic(new Topic(topicRequestModel.getTopic(), client.getBroker()));
            client.getBroker().getTopics().add(topic);
            return new ResponseEntity<>("Topic added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No MQTT client found", HttpStatus.PRECONDITION_FAILED);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "")
    public ResponseEntity<String> removeTopic(@Valid @RequestBody TopicRequestModel topicRequestModel) {
        MqttClientImpl client = mqttApplication.getByBrokerId(topicRequestModel.getBrokerId());
        Topic topicToRemove = topicRepo.getTopicByNameAndBorkerId(topicRequestModel.getTopic(), topicRequestModel.getBrokerId());
        if (client != null) {
            if (topicRepo.removeTopic(topicToRemove.getTopicId())) {
                Topic toRemove = null;
                for (Topic clientTopic : client.getBroker().getTopics()) {
                    if (clientTopic.getTopicId().equals(topicToRemove.getTopicId())) {
                        toRemove = clientTopic;
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

    @RequestMapping(method = RequestMethod.PATCH, value = "/subscribe")
    public ResponseEntity<String> subscribeTopic(@Valid @RequestBody TopicRequestModel topicRequestModel) throws MqttException {
        if(changeTopicSubStatus(topicRequestModel)){
            return new ResponseEntity<>("Topic: " + topicRequestModel.getTopic() + " subscribed", HttpStatus.OK);
        }
        return new ResponseEntity<>("Cannot subscribe topic", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/unsubscribe")
    public ResponseEntity<String> unsubscribeTopic(@Valid @RequestBody TopicRequestModel topicRequestModel) throws MqttException {
        if(changeTopicSubStatus(topicRequestModel)){
            return new ResponseEntity<>("Topic: " + topicRequestModel.getTopic() + " unsubscribed", HttpStatus.OK);
        }
        return new ResponseEntity<>("Cannot unsubscribe topic", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean changeTopicSubStatus(TopicRequestModel topicRequestModel) throws MqttException {
        Topic topic = topicRepo.getTopicByNameAndBorkerId(topicRequestModel.getTopic(), topicRequestModel.getBrokerId());
        MqttClientImpl client = mqttApplication.getByBrokerId(topicRequestModel.getBrokerId());
        if (client != null) {
            for (Topic clientTopic : client.getBroker().getTopics()) {
                if (clientTopic.getTopicId().equals(topic.getTopicId())) {
                    if (clientTopic.isSubscribed()) {
                        if (client.unsubscribeTopic(clientTopic.getTopic())) {
                            clientTopic.setSubscribed(false);
                            return true;
                        }
                    } else {
                        if (client.subscribeTopic(clientTopic.getTopic())) {
                            clientTopic.setSubscribed(true);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
