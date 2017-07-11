package com.michal.mqtt.api;

import com.michal.dao.PlacesDao;
import com.michal.mqtt.api.converter.request.PlaceRequestModelToPlaceConverter;
import com.michal.mqtt.api.converter.response.PlaceToPlaceResponseModelConverter;
import com.michal.mqtt.api.model.request.PlacesRequestModel;
import com.michal.mqtt.api.model.response.PlaceResponseModel;
import com.michal.mqtt.api.model.response.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/place")
public class PlacesApi {

    private PlacesDao placesDao;
    private PlaceToPlaceResponseModelConverter placeToPlaceResponseModelConverter;
    private PlaceRequestModelToPlaceConverter placeRequestModelToPlaceConverter;

    public PlacesApi(PlacesDao placesDao, PlaceToPlaceResponseModelConverter placeToPlaceResponseModelConverter, PlaceRequestModelToPlaceConverter placeRequestModelToPlaceConverter) {
        this.placesDao = placesDao;
        this.placeToPlaceResponseModelConverter = placeToPlaceResponseModelConverter;
        this.placeRequestModelToPlaceConverter = placeRequestModelToPlaceConverter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PlaceResponseModel>> getAllPlaces() {
        List<PlaceResponseModel> response = placeToPlaceResponseModelConverter.convert(placesDao.getAllPlaces());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SimpleResponse> addNewPlace(@Valid @RequestBody PlacesRequestModel placesRequestModel) {
        placesDao.addNewPlace(placeRequestModelToPlaceConverter.convert(placesRequestModel));
        return new ResponseEntity<>(SimpleResponse.create("Place added!!!"), HttpStatus.OK);
    }

}
