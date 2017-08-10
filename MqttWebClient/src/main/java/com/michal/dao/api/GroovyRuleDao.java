package com.michal.dao.api;

import com.michal.dao.model.rule.GroovyRule;

import java.util.List;

public interface GroovyRuleDao {

    GroovyRule create(GroovyRule node);
    List<GroovyRule> getAllNodes();
    GroovyRule get(Long id);
    boolean remove(Long id);

}
