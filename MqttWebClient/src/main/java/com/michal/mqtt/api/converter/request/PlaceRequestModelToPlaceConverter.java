package com.michal.mqtt.api.converter.request;

import com.michal.dao.model.Place;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.model.request.PlacesRequestModel;

public class PlaceRequestModelToPlaceConverter extends Converter<PlacesRequestModel, Place>{
    @Override
    public Place convert(PlacesRequestModel placesRequestModel) {
        Place place = new Place();
        if(placesRequestModel !=null){
            place.setPlace(placesRequestModel.getPlace());
            place.setRoom(placesRequestModel.getRoom());
        }
        return place;
    }
}
