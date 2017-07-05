package com.michal.mqtt.api;

import com.michal.mqtt.api.model.request.TopicRequestModelWithCallback;
import com.michal.mqtt.api.model.response.SimpleResponse;
import com.michal.mqtt.callback.topic.CallbackFactory;
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
@RequestMapping("/topics")
public class TopicsApi {

    private TopicDao topicRepo;
    private MqttApplication mqttApplication;
    private CallbackFactory callbackFactory;

    public TopicsApi(TopicDao topicRepo, MqttApplication mqttApplication, CallbackFactory callbackFactory) {
        this.topicRepo = topicRepo;
        this.mqttApplication = mqttApplication;
        this.callbackFactory = callbackFactory;
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<SimpleResponse> addTopicToBroker(@Valid @RequestBody TopicRequestModelWithCallback topicRequestModel) {
        MqttClientImpl client = mqttApplication.getByBrokerId(topicRequestModel.getBrokerId());
        if (client != null) {
            Topic topic = topicRepo.createTopic(new Topic(topicRequestModel.getTopic(), client.getBroker(), topicRequestModel.getTopicCallback()));
            client.getBroker().getTopics().add(topic);
            return new ResponseEntity<>(SimpleResponse.create("Topic added"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(SimpleResponse.create("No MQTT client found"), HttpStatus.PRECONDITION_FAILED);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "")
    public ResponseEntity<SimpleResponse> removeTopic(@Valid @RequestBody TopicRequestModel topicRequestModel) {
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
                return new ResponseEntity<>(SimpleResponse.create("Topic removed"),HttpStatus.OK);
            } else {
                return new ResponseEntity<>(SimpleResponse.create("Cannot find topic to remove"), HttpStatus.PRECONDITION_FAILED);
            }
        }
        return new ResponseEntity<>(SimpleResponse.create("No MQTT client found"), HttpStatus.PRECONDITION_FAILED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/subscribe")
    public ResponseEntity<SimpleResponse> subscribeTopic(@Valid @RequestBody TopicRequestModel topicRequestModel) throws MqttException {
        if (changeTopicSubStatus(topicRequestModel, true)) {
            return new ResponseEntity<>(SimpleResponse.create("Topic: " + topicRequestModel.getTopic() + " subscribed"), HttpStatus.OK);
        }
        return new ResponseEntity<>(SimpleResponse.create("Cannot subscribe topic"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/unsubscribe")
    public ResponseEntity<SimpleResponse> unsubscribeTopic(@Valid @RequestBody TopicRequestModel topicRequestModel) throws MqttException {
        if (changeTopicSubStatus(topicRequestModel, false)) {
            return new ResponseEntity<>(SimpleResponse.create("Topic: " + topicRequestModel.getTopic() + " unsubscribed"), HttpStatus.OK);
        }
        return new ResponseEntity<>(SimpleResponse.create("Cannot unsubscribe topic"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean changeTopicSubStatus(TopicRequestModel topicRequestModel, boolean subscribe) throws MqttException {
        Topic topic = topicRepo.getTopicByNameAndBorkerId(topicRequestModel.getTopic(), topicRequestModel.getBrokerId());
        MqttClientImpl client = mqttApplication.getByBrokerId(topicRequestModel.getBrokerId());
        if (client != null) {
            for (Topic clientTopic : client.getBroker().getTopics()) {
                if (clientTopic.getTopicId().equals(topic.getTopicId())) {
                    if (clientTopic.isSubscribed() && !subscribe) {
                        if (client.unsubscribeTopic(clientTopic.getTopic())) {
                            clientTopic.setSubscribed(false);
                            return true;
                        }
                    } else if(subscribe && !clientTopic.isSubscribed()){
                        if (client.subscribeTopic(clientTopic.getTopic(), callbackFactory.createCallback(topic.getCallbackEnum()))) {
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
