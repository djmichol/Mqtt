package com.michal.mqtt.api.groovyrule;

import com.michal.dao.api.GroovyRuleDao;
import com.michal.dao.model.rule.GroovyRule;
import com.michal.mqtt.api.converter.request.GroovyRuleRequestToGroovyRuleConverter;
import com.michal.mqtt.api.converter.response.GroovyRuleToGroovyRuleResponseConverter;
import com.michal.mqtt.api.groovyrule.model.request.GroovyRulRequest;
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
@RequestMapping("/groovyRule")
public class GroovyRuleApi {

    private GroovyRuleDao groovyRuleDao;
    private GroovyRuleToGroovyRuleResponseConverter groovyRuleToGroovyRuleResponseConverter;
    private GroovyRuleRequestToGroovyRuleConverter groovyRuleRequestToGroovyRuleConverter;

    public GroovyRuleApi(GroovyRuleDao groovyRuleDao, GroovyRuleToGroovyRuleResponseConverter groovyRuleToGroovyRuleResponseConverter, GroovyRuleRequestToGroovyRuleConverter
            groovyRuleRequestToGroovyRuleConverter) {
        this.groovyRuleDao = groovyRuleDao;
        this.groovyRuleToGroovyRuleResponseConverter = groovyRuleToGroovyRuleResponseConverter;
        this.groovyRuleRequestToGroovyRuleConverter = groovyRuleRequestToGroovyRuleConverter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<GroovyRuleResponse>> getAllRules() {
        List<GroovyRuleResponse> groovyRuleResponses = groovyRuleToGroovyRuleResponseConverter.convert(groovyRuleDao.getAllNodes());
        return new ResponseEntity<>(groovyRuleResponses, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<GroovyRuleResponse> addNewNode(@Valid @RequestBody GroovyRulRequest groovyRulRequest) {
        GroovyRule groovyRule = groovyRuleDao.create(groovyRuleRequestToGroovyRuleConverter.convert(groovyRulRequest));
        return new ResponseEntity<>(groovyRuleToGroovyRuleResponseConverter.convert(groovyRule), HttpStatus.OK);
    }
}
