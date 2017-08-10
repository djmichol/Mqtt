package com.michal.mqtt.api.groovyrule;

import com.michal.dao.api.ActionDao;
import com.michal.dao.api.GroovyRuleDao;
import com.michal.dao.model.rule.Action;
import com.michal.dao.model.rule.GroovyRule;
import com.michal.mqtt.api.converter.request.ActionRequestToActionConverter;
import com.michal.mqtt.api.converter.request.GroovyRuleRequestToGroovyRuleConverter;
import com.michal.mqtt.api.converter.response.ActionToActionResponseConverter;
import com.michal.mqtt.api.converter.response.GroovyRuleToGroovyRuleResponseConverter;
import com.michal.mqtt.api.groovyrule.model.request.ActionRequest;
import com.michal.mqtt.api.groovyrule.model.request.GroovyRulRequest;
import com.michal.mqtt.api.groovyrule.model.response.ActionResponse;
import com.michal.mqtt.api.groovyrule.model.response.GroovyRuleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/action")
public class ActionApi {

    private ActionDao actionDao;
    private ActionToActionResponseConverter actionToActionResponseConverter;
    private ActionRequestToActionConverter actionRequestToActionConverter;

    public ActionApi(ActionDao actionDao, ActionToActionResponseConverter actionToActionResponseConverter, ActionRequestToActionConverter actionRequestToActionConverter) {
        this.actionDao = actionDao;
        this.actionToActionResponseConverter = actionToActionResponseConverter;
        this.actionRequestToActionConverter = actionRequestToActionConverter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ActionResponse>> getAllRules() {
        List<ActionResponse> groovyRuleResponses = actionToActionResponseConverter.convert(actionDao.getAll());
        return new ResponseEntity<>(groovyRuleResponses, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ActionResponse> addNewNode(@Valid @RequestBody ActionRequest actionRequest) {
        Action action = actionDao.create(actionRequestToActionConverter.convert(actionRequest));
        return new ResponseEntity<>(actionToActionResponseConverter.convert(action), HttpStatus.OK);
    }
}