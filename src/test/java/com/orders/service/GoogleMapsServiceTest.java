package com.orders.service;

import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.GeocodingResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GoogleMapsServiceTest {

    @Mock
    private GeoApiContext geoApiContext;

    private GoogleMapsService googleMapsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        googleMapsService = new GoogleMapsService();
        googleMapsService.setGeoApiContext(geoApiContext);
    }

    @Test
    void geocode_shouldReturnGeocodingResults() throws ApiException, InterruptedException, IOException {
        // Arrange
        String address = "123 Main St";

        GeocodingResult[] expectedResults = new GeocodingResult[1];
        expectedResults[0] = new GeocodingResult();

        //when(GeocodingApi.geocode(geoApiContext, address)).thenReturn(mock(GeocodingApi.Request.class));
       // when(GeocodingApi.Request.await()).thenReturn(expectedResults);

        // Act
        GeocodingResult[] results = googleMapsService.geocode(address);

        // Assert
        assertEquals(expectedResults, results);
    }

    @Test
    void getDistanceMatrix_shouldReturnDistanceMatrix() throws ApiException, InterruptedException, IOException {
        // Arrange
        String[] origins = {"1.234,5.678"};
        String[] destinations = {"9.876,5.432"};

        DistanceMatrixElement element = new DistanceMatrixElement();
        //element.distance = new Distance();
        element.distance.inMeters = (long) 1000.0;

        DistanceMatrixRow row = new DistanceMatrixRow();
        row.elements = new DistanceMatrixElement[]{element};

       // DistanceMatrix expectedMatrix = new DistanceMatrix();
        //expectedMatrix.rows = new DistanceMatrixRow[]{row};

        //when(DistanceMatrixApi.newRequest(geoApiContext)).thenReturn(mock(DistanceMatrixApiRequest.class));
        //when(DistanceMatrixApiRequest.origins(origins)).thenReturn(mock(DistanceMatrixApiRequest.class));
        //when(DistanceMatrixApiRequest.destinations(destinations)).thenReturn(mock(DistanceMatrixApiRequest.class));
        //when(DistanceMatrixApiRequest.mode(TravelMode.DRIVING)).thenReturn(mock(DistanceMatrixApiRequest.class));
        //when(DistanceMatrixApiRequest.await()).thenReturn(expectedMatrix);

        // Act
        //DistanceMatrix matrix = googleMapsService.getDistanceMatrix(origins, destinations);

        // Assert
        //assertEquals(expectedMatrix, matrix);
    }
}
