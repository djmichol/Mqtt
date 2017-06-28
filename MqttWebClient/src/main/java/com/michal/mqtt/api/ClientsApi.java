package com.michal.mqtt.api;

import java.util.List;

import com.michal.mqtt.api.converter.MqttClientToClientModelConverter;
import com.michal.mqtt.api.model.request.BrokerRequestModel;
import com.michal.mqtt.api.model.response.ClientResponseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.dao.BrokerDao;
import com.michal.dao.model.Broker;
import com.michal.mqtt.MqttApplication;
import com.michal.mqtt.MqttClientImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientsApi {

    final static Logger logger = LogManager.getLogger(ClientsApi.class);

    private MqttApplication mqttApplication;
    private BrokerDao brokerRepo;
    private MqttClientToClientModelConverter mqttClientToClientModelConverter;

    public ClientsApi(MqttApplication mqttApplication, BrokerDao brokerRepo, MqttClientToClientModelConverter mqttClientToClientModelConverter) {
        this.mqttApplication = mqttApplication;
        this.brokerRepo = brokerRepo;
        this.mqttClientToClientModelConverter = mqttClientToClientModelConverter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientResponseModel>> loadClients() throws MqttException {
        List<ClientResponseModel> clientResponseModels = mqttClientToClientModelConverter.convert(mqttApplication.getBrokers());
        return new ResponseEntity<>(clientResponseModels, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addNewBroker(@Valid @RequestBody BrokerRequestModel brokerRequestModel) throws MqttException {
        Broker broker = brokerRepo.create(new Broker(brokerRequestModel.getUrl(), brokerRequestModel.getUser(), brokerRequestModel.getPassword(), brokerRequestModel.getCallbackEnum()));
        mqttApplication.getBrokers().add(new MqttClientImpl(broker, MqttApplication.CLIENT_ID));
        return new ResponseEntity<>("Broker added", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{brokerId}")
    public ResponseEntity<String> removeBroker(@PathVariable("brokerId") Long brokerId) {
        if (brokerRepo.removeBroker(brokerId)) {
            MqttClientImpl client = mqttApplication.getByBrokerId(brokerId);
            mqttApplication.getBrokers().remove(client);
            return new ResponseEntity<>("Broker removed", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find broker to remove", HttpStatus.PRECONDITION_FAILED);
        }
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/connect/{brokerId}")
    public ResponseEntity<String> connectToBroker(@PathVariable("brokerId") Long brokerId) throws MqttException {
        if (mqttApplication.getByBrokerId(brokerId).connect()) {
            return new ResponseEntity<>("Client connected", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/disconnect/{brokerId}")
    public ResponseEntity<String> disconnectToBroker(@PathVariable("brokerId") Long brokerId) throws MqttException {
        if (mqttApplication.getByBrokerId(brokerId).disconnect()) {
            return new ResponseEntity<>("Client disconnected", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}