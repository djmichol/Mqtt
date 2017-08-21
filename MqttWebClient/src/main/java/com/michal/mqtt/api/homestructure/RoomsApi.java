package com.michal.mqtt.api.homestructure;

import com.michal.dao.api.RoomDao;
import com.michal.dao.api.SensorDao;
import com.michal.dao.model.homestructure.Room;
import com.michal.mqtt.api.converter.request.RoomRequestModelToRoomConverter;
import com.michal.mqtt.api.converter.response.RoomToRoomDetailsResponseConverter;
import com.michal.mqtt.api.homestructure.model.request.RoomRequestModel;
import com.michal.mqtt.api.homestructure.model.response.RoomResponseModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/room")
public class RoomsApi {

    private RoomDao roomDao;
    private RoomToRoomDetailsResponseConverter roomToRoomResponseConverter;
    private RoomRequestModelToRoomConverter roomRequestModelToRoomConverter;
    private SensorDao sensorDao;

    public RoomsApi(RoomDao roomDao, RoomToRoomDetailsResponseConverter roomToRoomResponseConverter, RoomRequestModelToRoomConverter roomRequestModelToRoomConverter, SensorDao sensorDao) {
        this.roomDao = roomDao;
        this.roomToRoomResponseConverter = roomToRoomResponseConverter;
        this.roomRequestModelToRoomConverter = roomRequestModelToRoomConverter;
        this.sensorDao = sensorDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RoomResponseModel>> getAllRooms() {
        List<RoomResponseModel> response = roomToRoomResponseConverter.convert(roomDao.getAllRoom());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<RoomResponseModel> getRoomDetails(@PathVariable("id") Long roomId) {
        RoomResponseModel response = roomToRoomResponseConverter.convert(roomDao.get(roomId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RoomResponseModel> addNewRoom(@Valid @RequestBody RoomRequestModel roomRequestModel) {
        Room room = roomDao.addNewRoom(roomRequestModelToRoomConverter.convert(roomRequestModel));
        roomRequestModel.getSensors().forEach(sensorId -> sensorDao.addRoomToSensor(room, Long.decode(sensorId)));
        return new ResponseEntity<>(roomToRoomResponseConverter.convert(room), HttpStatus.OK);
    }

}
