package com.orders.entity;

import com.orders.config.OrderUtils;

import javax.persistence.*;


@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @Column(name="DISTANCE")
    private double distance;
    @Column(name="STATUS")
    private OrderUtils.OrderStatus status;
    @Column(name="ORIGIN")
    private String[] origin;
    @Column(name="DESTINATION")
    private String[] destination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public OrderUtils.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderUtils.OrderStatus status) {
        this.status = status;
    }

    public String[] getOrigin() {
        return origin;
    }

    public void setOrigin(String[] origin) {
        this.origin = origin;
    }

    public String[] getDestination() {
        return destination;
    }

    public void setDestination(String[] destination) {
        this.destination = destination;
    }
}
