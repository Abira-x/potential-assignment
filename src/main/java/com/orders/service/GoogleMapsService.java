package com.orders.service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleMapsService {


    private  GeoApiContext geoApiContext;

    public GeoApiContext getGeoApiContext() {
        return geoApiContext;
    }

    public void setGeoApiContext(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
    }

    /* public GoogleMapsService(String apiKey) {
        this.geoApiContext = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }*/

    public GeocodingResult[] geocode(String address) throws ApiException, InterruptedException, IOException {
        GeocodingResult[] results = GeocodingApi.geocode(geoApiContext, address).await();
        return results;
    }

    public DistanceMatrix getDistanceMatrix(String[] origins, String[] destinations) throws ApiException, InterruptedException, IOException {
        DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(geoApiContext)
                .origins(origins)
                .destinations(destinations)
                .mode(TravelMode.DRIVING)
                .await();
        return distanceMatrix;
    }
}