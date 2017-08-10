package com.michal.mqtt.engine.rulesengine;

import com.michal.dao.model.rule.GroovyRule;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.util.HashMap;
import java.util.Map;

public class GroovyRuleEngine {

    public Map<String, Object> getVariables(GroovyRule groovyRule, String data){
        Map<String, Object> variables = new HashMap<>();
        Object var = getVariable(groovyRule, data);
        variables.put(groovyRule.getSensor().getType(), var);
        return variables;
    }

    private Object getVariable(GroovyRule groovyRule, String data) {
        Object var;
        if(groovyRule.getType().equals(GroovyRule.VariableType.NUMBER)){
            var = new Integer(data);
        }else if(groovyRule.getType().equals(GroovyRule.VariableType.BOOLEAN)){
            var = new Boolean(data);
        }else{
            var = data;
        }
        return var;
    }

    public boolean evaluate(String script, Map<String, Object> variables){
        Binding binding = new Binding();
        variables.entrySet().forEach(stringObjectEntry -> binding.setVariable(stringObjectEntry.getKey(), stringObjectEntry.getValue()));
        GroovyShell groovyShell = new GroovyShell(binding);
        Object result = groovyShell.evaluate(script);
        return (boolean) result;
    }

}
