package com.orders.service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.Unit;
import com.orders.bean.OrderResponse;
import com.orders.config.OrderUtils;
import com.orders.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.orders.repository.OrderRepository;

import java.io.IOException;
import java.util.*;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private GeoApiContext geoApiContext;
    @Autowired
    public Order order;
    @Autowired
    public OrderService(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }
//can implement business logic here
    //private static int orderIdCounter = 1;
    private static Map<Long, Order> orders = new HashMap<>();

    public double calculateDistance(String[] origin, String[] destination) throws IOException, InterruptedException, ApiException {

        // TODO :: define the api key here...
        String apiKey = "YOUR_API_KEY";

        // Create a GeoApiContext object with the API key
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(geoApiContext)
                .origins(origin)
                .destinations(destination)
                .units(Unit.METRIC);

        DistanceMatrix result = request.await();

        if (result.rows.length > 0 && result.rows[0].elements.length > 0) {
            Distance distance = result.rows[0].elements[0].distance;
            return distance.inMeters;
        } else {
            throw new IllegalStateException("Distance calculation failed");
        }
    }

    public Long saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }

    public String retrieveOrderById(Long orderId) {
       Optional<Order> order =  orderRepository.findById(orderId);
        if(order.isPresent()) {
            if(order.get().getStatus().equals(OrderUtils.OrderStatus.TAKEN))
                return "Order already taken.";
            order.get().setStatus(OrderUtils.OrderStatus.TAKEN);
            orderRepository.save(order.get());
        } else {
            return "No order has been created with this order id";
        }
       return "SUCCESS";
    }

    public List<OrderResponse> getOrderList() {
        List<OrderResponse> orderResponseList = new ArrayList<>();
        List<Order> orderList = orderRepository.findAll();
        for(Order object : orderList) {
            OrderResponse orderResponse = new OrderResponse(object.getId(), object.getDistance(), object.getStatus());
            orderResponseList.add(orderResponse);
        }
        return orderResponseList;
    }

    //create new method and write the orderrepository.save(order) implementation here.
   // Similar for getid

    /*
    public Order createOrder(String[] originCoordinates,String[] destinationCoordinates) {
        // Use Google Maps API to calculate the distance between origin and destination coordinates
        double distance = calculateDistance(originCoordinates,destinationCoordinates);
        Order order = new Order(orderIdCounter++, distance, orderRepository.findById(id));
        orders.put(order.getId(), order);
        return order;
    }

    public Order takeOrder(int orderId) {
        Order order = orders.get(orderId);
        if (order != null && order.getStatus().equals("UNASSIGNED")) {
            order.setStatus("TAKEN");
            return order;
        }
        return null;
    }

    public List<Order> listOrders(int page, int limit) {
        int startIndex = (page - 1) * limit;
        List<Order> result = new ArrayList<>();
        int count = 0;
        for (Order order : orders.values()) {
            if (count >= startIndex && count < startIndex + limit) {
                result.add(order);
            }
            count++;
        }
        return result;
    }
*/

}
