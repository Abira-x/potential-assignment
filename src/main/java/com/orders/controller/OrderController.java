package com.orders.controller;

import com.orders.bean.OrderRequest;
import com.orders.bean.OrderResponse;
import com.google.maps.errors.ApiException;
import com.orders.config.OrderUtils;
import com.orders.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import com.orders.service.GoogleMapsService;
import com.orders.service.OrderService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RestController
@RequestMapping("/orders")
public class OrderController {
    private Map<String, Order> orders = new HashMap<>();

    @Autowired
    private GoogleMapsService googleMapsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private Order order;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) throws IOException, InterruptedException, ApiException {
        // TODO: Implement logic to calculate the distance and create the order
        double distance = orderService.calculateDistance(orderRequest.getOrigin(), orderRequest.getDestination());

        order.setOrigin(orderRequest.getOrigin());
        order.setDestination(orderRequest.getDestination());
        order.setDistance(distance);
        order.setStatus(OrderUtils.OrderStatus.CREATED);

        Long orderId = orderService.saveOrder(order);

        OrderResponse orderResponse = new OrderResponse(orderId, order.getDistance(), order.getStatus());
        return ResponseEntity.ok(orderResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> takeOrder(@PathVariable("id") Long id, @RequestBody String status) {
        try {
            String value = orderService.retrieveOrderById(id);
            if ("SUCCESS".equalsIgnoreCase(value)) {
                return ResponseEntity.status(HttpStatus.FOUND).body("SUCCESS");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(value);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Order status not confirmed");
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> listOrders(
            @RequestParam("page") int page, @RequestParam("limit") int limit) {
        if (page < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        int startIndex = (page - 1) * limit;
        int endIndex = Math.min(startIndex + limit, orders.size());
        List<OrderResponse> paginatedListOrders = orderService.getOrderList();
        return ResponseEntity.ok(paginatedListOrders);
    }
}



