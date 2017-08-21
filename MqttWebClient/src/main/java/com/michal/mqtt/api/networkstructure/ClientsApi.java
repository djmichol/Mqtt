package com.michal.mqtt.api.networkstructure;

import java.util.List;
import java.util.Optional;

import com.michal.dao.api.ReceivedMessageDao;
import com.michal.dao.api.SendMessageDao;
import com.michal.mqtt.api.converter.request.BrokerModelToBrokerConverter;
import com.michal.mqtt.api.converter.response.BrokerToClientModelConverter;
import com.michal.mqtt.api.networkstructure.model.request.BrokerRequestModel;
import com.michal.mqtt.api.networkstructure.model.response.ClientResponseModel;
import com.michal.mqtt.api.utils.SimpleResponse;
import com.michal.mqtt.engine.client.ReceivedMessageExtractor;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.dao.api.BrokerDao;
import com.michal.dao.model.networkstructure.Broker;
import com.michal.mqtt.MqttApplication;
import com.michal.mqtt.MqttClientImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/brokers")
public class ClientsApi {

    private MqttApplication mqttApplication;
    private BrokerDao brokerRepo;
    private BrokerToClientModelConverter brokerToClientModelConverter;
    private BrokerModelToBrokerConverter brokerModelToBrokerConverter;
    private SendMessageDao sendMessageDao;
    private ReceivedMessageDao receivedMessageDao;
    private ReceivedMessageExtractor receivedMessageExtractor;

    public ClientsApi(MqttApplication mqttApplication, BrokerDao brokerRepo, BrokerToClientModelConverter brokerToClientModelConverter, BrokerModelToBrokerConverter brokerModelToBrokerConverter, SendMessageDao sendMessageDao, ReceivedMessageDao receivedMessageDao, ReceivedMessageExtractor receivedMessageExtractor) {
        this.mqttApplication = mqttApplication;
        this.brokerRepo = brokerRepo;
        this.brokerToClientModelConverter = brokerToClientModelConverter;
        this.brokerModelToBrokerConverter = brokerModelToBrokerConverter;
        this.sendMessageDao = sendMessageDao;
        this.receivedMessageDao = receivedMessageDao;
        this.receivedMessageExtractor = receivedMessageExtractor;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientResponseModel>> getAllBrokers() throws MqttException {
        List<ClientResponseModel> brokers = brokerToClientModelConverter.convert(brokerRepo.getAllBrokers());
        return new ResponseEntity<>(brokers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{brokerId}")
    public ResponseEntity<ClientResponseModel> getBrokerDetails(@PathVariable("brokerId") Long brokerId) {
        ClientResponseModel broker = brokerToClientModelConverter.convert(brokerRepo.get(brokerId));
        return new ResponseEntity<>(broker, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ClientResponseModel> addNewBroker(@Valid @RequestBody BrokerRequestModel brokerRequestModel) throws MqttException {
        Broker broker = brokerRepo.create(brokerModelToBrokerConverter.convert(brokerRequestModel));
        mqttApplication.getBrokers().add(new MqttClientImpl(broker, MqttApplication.CLIENT_ID, sendMessageDao, receivedMessageDao, receivedMessageExtractor, brokerRepo));
        return new ResponseEntity<>(brokerToClientModelConverter.convert(broker), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{brokerId}")
    public ResponseEntity<SimpleResponse> removeBroker(@PathVariable("brokerId") Long brokerId) {
        if (brokerRepo.remove(brokerId)) {
            Optional<MqttClientImpl> client = mqttApplication.getByBrokerId(brokerId);
            mqttApplication.getBrokers().remove(client.orElseThrow(NullPointerException::new));
            return new ResponseEntity<>(SimpleResponse.create("Broker removed"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(SimpleResponse.create("Cannot find broker to remove"), HttpStatus.PRECONDITION_FAILED);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/connect/{brokerId}")
    public ResponseEntity<SimpleResponse> connectToBroker(@PathVariable("brokerId") Long brokerId) throws MqttException {
        Optional<MqttClientImpl> client = mqttApplication.getByBrokerId(brokerId);
        if (client.orElseThrow(() -> new MqttException(MqttException.REASON_CODE_BROKER_UNAVAILABLE)).connect()) {
            return new ResponseEntity<>(SimpleResponse.create("Client connected"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/disconnect/{brokerId}")
    public ResponseEntity<SimpleResponse> disconnectToBroker(@PathVariable("brokerId") Long brokerId) throws MqttException {
        Optional<MqttClientImpl> client = mqttApplication.getByBrokerId(brokerId);
        if (client.orElseThrow(() -> new MqttException(MqttException.REASON_CODE_BROKER_UNAVAILABLE)).disconnect()) {
            return new ResponseEntity<>(SimpleResponse.create("Client disconnected"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
