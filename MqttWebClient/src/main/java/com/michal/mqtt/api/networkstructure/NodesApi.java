package com.michal.mqtt.api.networkstructure;

import com.michal.dao.api.NodeDao;
import com.michal.dao.model.networkstructure.Node;
import com.michal.mqtt.api.converter.request.NodeRequestToBreokerNodeConverter;
import com.michal.mqtt.api.converter.response.NodeToNodeResponseConverter;
import com.michal.mqtt.api.networkstructure.model.request.NodeRequestModel;
import com.michal.mqtt.api.networkstructure.model.response.NodeResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/node")
public class NodesApi {

    private NodeDao nodeDao;
    private NodeToNodeResponseConverter nodeToNodeResponseConverter;
    private NodeRequestToBreokerNodeConverter nodeRequestToBreokerNodeConverter;

    public NodesApi(NodeDao nodeDao, NodeToNodeResponseConverter nodeToNodeResponseConverter, NodeRequestToBreokerNodeConverter
            nodeRequestToBreokerNodeConverter) {
        this.nodeDao = nodeDao;
        this.nodeToNodeResponseConverter = nodeToNodeResponseConverter;
        this.nodeRequestToBreokerNodeConverter = nodeRequestToBreokerNodeConverter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<NodeResponseModel>> getAllNodes() {
        List<NodeResponseModel> nodeResponseModels = nodeToNodeResponseConverter.convert(nodeDao.getAllNodes());
        return new ResponseEntity<>(nodeResponseModels, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET , value = "/broker/{id}")
    public ResponseEntity<List<NodeResponseModel>> getNodesByBrokerId(@PathVariable("id") Long brokerId) {
        List<NodeResponseModel> nodeResponseModels = nodeToNodeResponseConverter.convert(nodeDao.getByBrokerId(brokerId));
        return new ResponseEntity<>(nodeResponseModels, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<NodeResponseModel> getNodeDetails(@PathVariable("id") Long nodeId) {
        NodeResponseModel sensorResponseModel = nodeToNodeResponseConverter.convert(nodeDao.get(nodeId));
        return new ResponseEntity<>(sensorResponseModel, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<NodeResponseModel> addNewNode(@Valid @RequestBody NodeRequestModel brokerRequestModel) {
        Node node = nodeDao.create(nodeRequestToBreokerNodeConverter.convert(brokerRequestModel));
        return new ResponseEntity<>(nodeToNodeResponseConverter.convert(node), HttpStatus.OK);
    }
}
