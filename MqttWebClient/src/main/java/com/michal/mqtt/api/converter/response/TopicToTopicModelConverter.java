package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.Topic;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.model.response.TopicResponseModel;

public class TopicToTopicModelConverter extends Converter<Topic, TopicResponseModel> {

    @Override
    public TopicResponseModel convert(Topic topic) {
        TopicResponseModel topicResponse = new TopicResponseModel();
        if (topic.getBroker() != null) {
            topicResponse.setBrokerId(topic.getBroker().getId());
            topicResponse.setSubscribed(topic.isSubscribed());
            topicResponse.setTopic(topic.getTopic());
        }
        return topicResponse;
    }
}
