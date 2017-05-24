package com.michal.mqtt.rest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.app.Application;
import com.michal.dao.BrokerDao;
import com.michal.dao.TopicDao;
import com.michal.model.Broker;
import com.michal.model.Topic;
import com.michal.mqtt.MqttClientImpl;

@RestController
@RequestMapping("/topic")
public class TopicsApi {

	@Autowired
	private BrokerDao brokerRepo;
	@Autowired
	private TopicDao topicRepo;
	@Autowired
	private Application application;

	@RequestMapping(method = RequestMethod.POST, value = "/brokerId={brokerId}")
	public ResponseEntity<?> addTopicToBroker(@PathVariable("brokerId") Long brokerId, @RequestBody String topicData) {
		Broker broker = brokerRepo.getById(brokerId);
		if (broker != null) {
			JSONObject data = new JSONObject(topicData);
			Topic topic = new Topic(data.getString("topic"), broker);
			topicRepo.createTopic(topic);
			broker.getTopics().add(topic);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/brokerId={brokerId}/topicId={topicId}")
	public ResponseEntity<?> removeTopic(@PathVariable("brokerId") Long brokerId, @PathVariable("topicId") Long topicId) {
		Broker broker = brokerRepo.getById(brokerId);
		if (broker != null) {
			if (topicRepo.removeTopic(topicId)) {
				Topic toRemove = null;
				for (Topic topic : broker.getTopics()) {
					if (topic.getTopicId().equals(topicId)) {
						toRemove = topic;
					}
				}
				if (toRemove != null) {
					broker.getTopics().remove(toRemove);
				}
				return new ResponseEntity<String>(HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
			}
		}
		return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/brokerId={brokerId}/subscribe/topicId={topicId}")
	public ResponseEntity<String> subscribeTopic(@PathVariable("brokerId") Long brokerId, @PathVariable("topicId") Long topicId) {
		MqttClientImpl client = application.getByBrokerId(brokerId);
		if (client != null) {
			for (Topic topic : client.getBroker().getTopics()) {
				if (topic.getTopicId().equals(topicId)) {
					if (client.subscribeTopic(topic.getTopic())) {
						topic.setSubscribed(true);
						return new ResponseEntity<String>(HttpStatus.OK);
					}
				}
			}
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/brokerId={brokerId}/unsubscribe/topicId={topicId}")
	public ResponseEntity<String> unsubscribeTopic(@PathVariable("brokerId") Long brokerId, @PathVariable("topicId") Long topicId) {
		MqttClientImpl client = application.getByBrokerId(brokerId);
		if (client != null) {
			for (Topic topic : client.getBroker().getTopics()) {
				if (topic.getTopicId().equals(topicId)) {
					if (client.unsubscribeTopic(topic.getTopic())) {
						topic.setSubscribed(false);
						return new ResponseEntity<String>(HttpStatus.OK);
					}
				}
			}
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
