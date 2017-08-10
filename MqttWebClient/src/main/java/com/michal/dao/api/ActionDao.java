package com.michal.dao.api;

import com.michal.dao.model.rule.Action;

import java.util.List;

public interface ActionDao {

    Action create(Action action);
    List<Action> getAll();
    Action get(Long id);
    boolean remove(Long id);
}
