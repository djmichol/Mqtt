package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.Place;
import com.michal.mqtt.api.model.response.PlaceResponseModel;
import com.michal.mqtt.api.converter.Converter;

public class PlaceToPlaceResponseModelConverter extends Converter<Place,PlaceResponseModel> {

    @Override
    public PlaceResponseModel convert(Place place) {
        PlaceResponseModel placeResponseModel = new PlaceResponseModel();
        if(place!=null){
            placeResponseModel.setPlace(place.getPlace());
            placeResponseModel.setRoom(place.getRoom());
        }
        return placeResponseModel;
    }
}
