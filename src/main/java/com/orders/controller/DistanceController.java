package com.orders.controller;

import com.google.maps.GeoApiContext;

public class DistanceController {
    private GeoApiContext geoApiContext;

    public DistanceController(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
    }
}
