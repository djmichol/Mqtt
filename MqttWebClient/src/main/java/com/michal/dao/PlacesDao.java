package com.michal.dao;

import com.michal.dao.model.Place;

import java.util.List;

public interface PlacesDao {

    Place addNewPlace(Place place);
    List<Place> getAllPlaces();

}
