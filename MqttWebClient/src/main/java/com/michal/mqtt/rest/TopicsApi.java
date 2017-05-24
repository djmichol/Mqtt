package com.michal.mqtt.rest;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.dao.TopicDao;
import com.michal.model.Topic;
import com.michal.mqtt.Application;
import com.michal.mqtt.MqttClientImpl;

@RestController
@RequestMapping("/topic")
public class TopicsApi {

	@Autowired
	private TopicDao topicRepo;
	@Autowired
	private Application application;

	@RequestMapping(method = RequestMethod.POST, value = "/brokerId={brokerId}")
	public ResponseEntity<?> addTopicToBroker(@PathVariable("brokerId") Long brokerId, @RequestBody String topicData) {
		MqttClientImpl client = application.getByBrokerId(brokerId);
		if (client != null) {
			JSONObject data = new JSONObject(topicData);
			Topic topic = new Topic(data.getString("topic"), client.getBroker());
			topicRepo.createTopic(topic);
			client.getBroker().getTopics().add(topic);
			return new ResponseEntity<String>("Topic added",HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("No MQTT client found",HttpStatus.PRECONDITION_FAILED);
		}
		
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/brokerId={brokerId}/topicId={topicId}")
	public ResponseEntity<?> removeTopic(@PathVariable("brokerId") Long brokerId, @PathVariable("topicId") Long topicId) {
		MqttClientImpl client = application.getByBrokerId(brokerId);
		if (client != null) {
			if (topicRepo.removeTopic(topicId)) {
				Topic toRemove = null;
				for (Topic topic : client.getBroker().getTopics()) {
					if (topic.getTopicId().equals(topicId)) {
						toRemove = topic;
					}
				}
				if (toRemove != null) {
					client.getBroker().getTopics().remove(toRemove);
				}
				return new ResponseEntity<String>(HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Cannot find topic to remove",HttpStatus.PRECONDITION_FAILED);
			}
		}
		return new ResponseEntity<String>("No MQTT client found",HttpStatus.PRECONDITION_FAILED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/brokerId={brokerId}/subscribe/topicId={topicId}")
	public ResponseEntity<String> subscribeTopic(@PathVariable("brokerId") Long brokerId, @PathVariable("topicId") Long topicId) {
		MqttClientImpl client = application.getByBrokerId(brokerId);
		if (client != null) {
			for (Topic topic : client.getBroker().getTopics()) {
				if (topic.getTopicId().equals(topicId)) {
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

	@RequestMapping(method = RequestMethod.POST, value = "/brokerId={brokerId}/unsubscribe/topicId={topicId}")
	public ResponseEntity<String> unsubscribeTopic(@PathVariable("brokerId") Long brokerId, @PathVariable("topicId") Long topicId) {
		MqttClientImpl client = application.getByBrokerId(brokerId);
		if (client != null) {
			for (Topic topic : client.getBroker().getTopics()) {
				if (topic.getTopicId().equals(topicId)) {
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

	@RequestMapping(method = RequestMethod.POST, value = "/brokerId={brokerId}/publish/topicId={topicId}")
	public ResponseEntity<String> publishToTopic(@PathVariable("brokerId") Long brokerId, @PathVariable("topicId") Long topicId, @RequestBody String message) {
		MqttClientImpl client = application.getByBrokerId(brokerId);
		if (client != null) {
			for (Topic topic : client.getBroker().getTopics()) {
				if (topic.getTopicId().equals(topicId)) {
					client.publish(topic.getTopic(), message, 0);
					return new ResponseEntity<String>("Message to topic: " + topic.getTopic() + " published", HttpStatus.OK);
				}
			}
			return new ResponseEntity<String>("No topic found", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<String>("No MQTT client found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/brokerId={brokerId}/publish")
	public ResponseEntity<String> publishMessage(@PathVariable("brokerId") Long brokerId, @RequestBody String messageWithTopic) {
		MqttClientImpl client = application.getByBrokerId(brokerId);
		if (client != null) {
			JSONObject data = new JSONObject(messageWithTopic);
			String topic = data.getString("topic");
			client.publish(topic, data.getString("message"), 0);
			return new ResponseEntity<String>("Message to topic: " + topic + " published", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No MQTT client found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
