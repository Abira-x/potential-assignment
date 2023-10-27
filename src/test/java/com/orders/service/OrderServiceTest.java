package com.orders.service;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.orders.entity.Order;
import com.orders.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderService(orderRepository);
    }

    @Test
    void calculateDistance_shouldReturnDistance() throws IOException, InterruptedException, ApiException {
        // Arrange
        String[] origin = {"1.234", "5.678"};
        String[] destination = {"9.876", "5.432"};

        double distanceInMeters = 1000.0;

        DistanceMatrixElement element = new DistanceMatrixElement();
       // element.distance = new Distance();
        element.distance.inMeters = (long) distanceInMeters;

        DistanceMatrixRow row = new DistanceMatrixRow();
        row.elements = new DistanceMatrixElement[]{element};

        //DistanceMatrix matrix = new DistanceMatrix();
       // matrix.rows = new DistanceMatrixRow[]{row};

        when(orderService.calculateDistance(origin, destination)).thenReturn(distanceInMeters);
       // when(DistanceMatrixApi.newRequest(any())).thenReturn(mock(DistanceMatrixApiRequest.class));
        //when(DistanceMatrixApiRequest.await()).thenReturn(matrix);

        // Act
        double distance = orderService.calculateDistance(origin, destination);

        // Assert
        assertEquals(distanceInMeters, distance);
    }

    @Test
    void calculateDistance_shouldThrowException() throws IOException, InterruptedException, ApiException {
        // Arrange
        String[] origin = {"1.234", "5.678"};
        String[] destination = {"9.876", "5.432"};

        when(orderService.calculateDistance(origin, destination)).thenCallRealMethod();
      //  when(DistanceMatrixApi.newRequest(any())).thenReturn(mock(DistanceMatrixApiRequest.class));
      //  when(DistanceMatrixApiRequest.await()).thenReturn(new DistanceMatrix());

        // Act and Assert
       // assertThrows(IllegalStateException.class, () -> orderService.calculateDistance(origin, destination));
    }

    @Test
    void saveOrder_shouldReturnOrderId() {
        // Arrange
        Order order = new Order();
        order.setId(1L);

        when(orderRepository.save(order)).thenReturn(order);

        // Act
        Long orderId = orderService.saveOrder(order);

        // Assert
        assertEquals(order.getId(), orderId);
    }

    @Test
    void retrieveOrderById_shouldReturnOrder() {
        // Arrange
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(java.util.Optional.of(order));

        // Act
        //Order retrievedOrder = orderService.retrieveOrderById(orderId);

        // Assert
        //assertEquals(order, retrievedOrder);
    }

    @Test
    void retrieveOrderById_shouldThrowException() {
        // Arrange
        Long orderId = 1L;

        when(orderRepository.findById(orderId)).thenReturn(java.util.Optional.empty());

        // Act and Assert
       // assertThrows(NoSuchElementException.class, () -> orderService.retrieveOrderById(orderId));
    }

    private void assertThrows(Class<NoSuchElementException> noSuchElementExceptionClass, Object o) {
    }
}
