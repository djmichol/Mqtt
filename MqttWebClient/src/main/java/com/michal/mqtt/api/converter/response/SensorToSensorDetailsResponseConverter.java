package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.networkstructure.Sensor;
import com.michal.mqtt.api.converter.ResponseConverter;
import com.michal.mqtt.api.homestructure.RoomsApi;
import com.michal.mqtt.api.networkstructure.SensorsApi;
import com.michal.mqtt.api.networkstructure.model.response.SensorResponseModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class SensorToSensorDetailsResponseConverter extends ResponseConverter<Sensor, SensorResponseModel> {

    @Override
    public SensorResponseModel convert(Sensor sensor) {
        if (sensor != null) {
            SensorResponseModel sensorResponseModel = new SensorResponseModel();
            sensorResponseModel.setLastSeen(sensor.getLastSeen());
            sensorResponseModel.setName(sensor.getName());
            sensorResponseModel.setNodeId(sensor.getNode().getId());
            sensorResponseModel.setNodeName(sensor.getNode().getName());
            sensorResponseModel.setType(sensor.getType());
            sensorResponseModel.setTopic(sensor.getTopic());
            prepareLinks(sensor, sensorResponseModel);
            return sensorResponseModel;
        }
        return null;
    }

    @Override
    protected void prepareLinks(Sensor sensor, SensorResponseModel sensorResponseModel) {
        if (sensor.getRoom() != null) {
            Link room = linkTo(methodOn(RoomsApi.class).getRoomDetails(sensor.getRoom().getId())).withRel("sensor.room");
            sensorResponseModel.add(room);
        }
        Link detail = linkTo(SensorsApi.class).slash(sensor.getId()).withSelfRel();
        sensorResponseModel.add(detail);

    }
}
