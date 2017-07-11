package com.michal.dao.impl;

import com.michal.dao.PlacesDao;
import com.michal.dao.model.Place;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class PlacesDaoImpl implements PlacesDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Place addNewPlace(Place place) {
        entityManager.persist(place);
        entityManager.flush();
        return place;
    }

    @Override
    public List<Place> getAllPlaces() {
        return entityManager.createQuery("from Place").getResultList();
    }

}
