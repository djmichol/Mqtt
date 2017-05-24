package com.michal.mqtt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.app.Application;
import com.michal.dao.MessageDao;
import com.michal.model.Message;
import com.michal.model.Topic;
import com.michal.mqtt.MqttClientImpl;

@RestController
@RequestMapping("/message")
public class MessageApi {

	@Autowired
	private Application application;
	@Autowired
	private MessageDao messageRepo;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Message>> getMessages() {
		List<Message> messages = messageRepo.getAllMessages();
		return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/brokerId={brokerId}/publish/topicId={topicId}")
	public ResponseEntity<String> publishToTopic(@PathVariable("brokerId") Long brokerId, @PathVariable("topicId") Long topicId, @RequestBody String message) {
		MqttClientImpl client = application.getByBrokerId(brokerId);
		if (client != null) {
			for (Topic topic : client.getBroker().getTopics()) {
				if (topic.getTopicId().equals(topicId)) {
					client.publish(topic.getTopic(), message, 0);
					return new ResponseEntity<String>(HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
