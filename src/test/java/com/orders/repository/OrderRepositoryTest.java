/*
package com.orders.repository;


import com.orders.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testSaveOrder() {
        // Arrange
        Order order = new Order();
        order.setDistance(10.5);
*/
/*
        order.setTaken(false);
        order.setStatus("PENDING");
        order.setOrigin("123 Main St");
        order.setDestination("456 Park Ave");
        order.setOrderId(100L);
        order.setOriginCoordinates(new String[]{"1.234,5.678"});
        order.setDestinationCoordinates(new String[]{"9.876,5.432"});
*//*


        // Act
        Order savedOrder = orderRepository.save(order);

        // Assert
        assertNotNull(savedOrder.getId());
        assertEquals(order.getDistance(), savedOrder.getDistance());
        assertEquals(order.isTaken(), savedOrder.isTaken());
        assertEquals(order.getStatus(), savedOrder.getStatus());
        assertEquals(order.getOrigin(), savedOrder.getOrigin());
        assertEquals(order.getDestination(), savedOrder.getDestination());
        assertEquals(order.getOrderId(), savedOrder.getOrderId());
        assertArrayEquals(order.getOriginCoordinates(), savedOrder.getOriginCoordinates());
        assertArrayEquals(order.getDestinationCoordinates(), savedOrder.getDestinationCoordinates());
    }

    @Test
    void testFindById() {
        // Arrange
        Order order = new Order();
        order.setDistance(10.5);
        order.setTaken(false);
        order.setStatus("PENDING");
        order.setOrigin("123 Main St");
        order.setDestination("456 Park Ave");
        order.setOrderId(100L);
        order.setOriginCoordinates(new String[]{"1.234,5.678"});
        order.setDestinationCoordinates(new String[]{"9.876,5.432"});

        Order savedOrder = orderRepository.save(order);
        Long orderId = savedOrder.getId();

        // Act
        Optional<Order> foundOrder = orderRepository.findById(orderId);

        // Assert
        assertTrue(foundOrder.isPresent());
        assertEquals(orderId, foundOrder.get().getId());
        assertEquals(order.getDistance(), foundOrder.get().getDistance());
        assertEquals(order.isTaken(), foundOrder.get().isTaken());
        assertEquals(order.getStatus(), foundOrder.get().getStatus());
        assertEquals(order.getOrigin(), foundOrder.get().getOrigin());
        assertEquals(order.getDestination(), foundOrder.get().getDestination());
        assertEquals(order.getOrderId(), foundOrder.get().getOrderId());
        assertArrayEquals(order.getOriginCoordinates(), foundOrder.get().getOriginCoordinates());
        assertArrayEquals(order.getDestinationCoordinates(), foundOrder.get().getDestinationCoordinates());
    }

    @Test
    void testFindByStatus() {
        // Arrange
        Order order1 = new Order();
        order1.setStatus("PENDING");
        orderRepository.save(order1);

        Order order2 = new Order();
        order2.setStatus("DELIVERED");
        orderRepository.save(order2);

        Order order3 = new Order();
        order3.setStatus("PENDING");
        orderRepository.save(order3);

        // Act
        //List<Order> pendingOrders = orderRepository.findByStatus("PENDING");

        // Assert
       // assertEquals(2, pendingOrders.size());
       // assertTrue(pendingOrders.stream().allMatch(order -> order.getStatus().equals("PENDING")));
    }
}*/
