package com.michal.dao.api;

import com.michal.dao.model.networkstructure.Node;

import java.util.List;

public interface NodeDao {

    Node create(Node node);
    List<Node> getAllNodes();
    List<Node> getByBrokerId(Long brokerId);
    Node get(Long id);
    boolean remove(Long id);
}
