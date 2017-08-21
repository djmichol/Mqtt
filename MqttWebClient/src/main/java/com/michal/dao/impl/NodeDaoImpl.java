package com.michal.dao.impl;

import com.michal.dao.api.NodeDao;
import com.michal.dao.model.networkstructure.Node;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


public class NodeDaoImpl implements NodeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Node create(Node node) {
        entityManager.persist(node);
        entityManager.flush();
        return node;
    }

    @Transactional
    @Override
    public List<Node> getAllNodes() {
        return entityManager.createQuery("Select node from Node node").getResultList();
    }

    @Transactional
    @Override
    public List<Node> getByBrokerId(Long brokerId) {
        Query query = entityManager.createQuery("Select data from Node data where data.broker.id = :brokerId", Node.class);
        query.setParameter("brokerId", brokerId);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Node get(Long id) {
        return entityManager.find(Node.class, id);
    }

    @Override
    @Transactional
    public boolean remove(Long id) {
        Node node = get(id);
        if (node != null) {
            entityManager.remove(node);
            return true;
        } else {
            return false;
        }
    }
}