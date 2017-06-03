package com.michal.mqtt.rest;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.dao.TopicDao;
import com.michal.model.Topic;
import com.michal.mqtt.Application;
import com.michal.mqtt.MqttClientImpl;
import com.michal.mqtt.rest.model.TopicData;

@RestController
@RequestMapping("/client/topic")
public class TopicsApi {

	@Autowired
	private TopicDao topicRepo;
	@Autowired
	private Application application;

	@RequestMapping(method = RequestMethod.POST, value = "")
	public ResponseEntity<?> addTopicToBroker(@RequestBody TopicData topicData) {
		MqttClientImpl client = application.getByBrokerId(topicData.getBrokerId());
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
		MqttClientImpl client = application.getByBrokerId(topicData.getBrokerId());
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
				return new ResponseEntity<String>("Cannot find topic to remove", HttpStatus.PRECONDITION_FAILED);
			}
		}
		return new ResponseEntity<String>("No MQTT client found", HttpStatus.PRECONDITION_FAILED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/subscribe")
	public ResponseEntity<String> subscribeTopic(@RequestBody TopicData topicData) {
		MqttClientImpl client = application.getByBrokerId(topicData.getBrokerId());
		if (client != null) {
			for (Topic topic : client.getBroker().getTopics()) {
				if (topic.getTopicId().equals(topicData.getTopicId())) {
					try {
						if (client.subscribeTopic(topic.getTopic())) {
							topic.setSubscribed(true);
							return new ResponseEntity<String>("Topic: " + topic.getTopic() + " subscribed", HttpStatus.OK);
						}
					} catch (MqttException e) {
						return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
			}
		}
		return new ResponseEntity<String>("Cannot subscribe topic", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/unsubscribe")
	public ResponseEntity<String> unsubscribeTopic(@RequestBody TopicData topicData) {
		MqttClientImpl client = application.getByBrokerId(topicData.getBrokerId());
		if (client != null) {
			for (Topic topic : client.getBroker().getTopics()) {
				if (topic.getTopicId().equals(topicData.getTopicId())) {
					try {
						if (client.unsubscribeTopic(topic.getTopic())) {
							topic.setSubscribed(false);
							return new ResponseEntity<String>("Topic: " + topic.getTopic() + " unsubscribed", HttpStatus.OK);
						}
					} catch (MqttException e) {
						return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
			}
		}
		return new ResponseEntity<String>("Cannot unsubscribe topic", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
