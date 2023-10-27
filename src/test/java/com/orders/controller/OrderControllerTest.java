package com.orders.controller;

import com.google.maps.errors.ApiException;
import com.orders.bean.OrderRequest;
import com.orders.bean.OrderResponse;
import com.orders.config.OrderUtils;
import com.orders.service.GoogleMapsService;
import com.orders.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private GoogleMapsService googleMapsService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() throws IOException, InterruptedException, ApiException {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrigin(new String[]{"1.0", "2.0"});
        orderRequest.setDestination(new String[]{"3.0", "4.0"});

        when(orderService.calculateDistance(orderRequest.getOrigin(), orderRequest.getDestination()))
                .thenReturn(10.0);

        when(orderService.saveOrder(any())).thenReturn(1L);

        ResponseEntity<OrderResponse> response = orderController.createOrder(orderRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
       // assertEquals(1L, response.getBody().getOrderId());
        assertEquals(10.0, response.getBody().getDistance());
        assertEquals("CREATED", response.getBody().getStatus());
    }

    @Test
    void testTakeOrder_Success() {
        Long orderId = 1L;

        when(orderService.retrieveOrderById(orderId)).thenReturn("SUCCESS");

        ResponseEntity<String> response = orderController.takeOrder(orderId, "TAKEN");

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());

        verify(orderService, times(1)).retrieveOrderById(orderId);
    }

    @Test
    void testTakeOrder_OrderNotFound() {
        Long orderId = 1L;

        when(orderService.retrieveOrderById(orderId)).thenReturn("Order not found");

        ResponseEntity<String> response = orderController.takeOrder(orderId, "TAKEN");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Order not found", response.getBody());

        verify(orderService, times(1)).retrieveOrderById(orderId);
    }

    @Test
    void testTakeOrder_StatusNotConfirmed() {
        Long orderId = 1L;

        when(orderService.retrieveOrderById(orderId)).thenThrow(new RuntimeException("Order status not confirmed"));

        ResponseEntity<String> response = orderController.takeOrder(orderId, "TAKEN");

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Order status not confirmed", response.getBody());

        verify(orderService, times(1)).retrieveOrderById(orderId);
    }

    @Test
    void testListOrders() {
        int page = 1;
        int limit = 10;

        List<OrderResponse> orderList = Arrays.asList(
                new OrderResponse(1L, 10.0, OrderUtils.OrderStatus.CREATED),
                new OrderResponse(2L, 20.0, OrderUtils.OrderStatus.TAKEN)
        );

        when(orderService.getOrderList()).thenReturn(orderList);

        ResponseEntity<List<OrderResponse>> response = orderController.listOrders(page, limit);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderList, response.getBody());

        verify(orderService, times(1)).getOrderList();
    }
}