package com.orders.bean;

public class OrderBean {


    private Long id;

    private double distance;

    private boolean taken;

    private String status;

    private String origin;

    private String destination;

    private Long orderId;

    private String[] originCoordinates;

    private String[] destinationCoordinates;

    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean isTaken() {
        return taken;
    }
    public void setTaken(boolean taken) {
        this.taken = taken;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String[] getOriginCoordinates() {
        return originCoordinates;
    }

    public void setOriginCoordinates(String[] originCoordinates) {
        this.originCoordinates = originCoordinates;
    }

    public String[] getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public void setDestinationCoordinates(String[] destinationCoordinates) {
        this.destinationCoordinates = destinationCoordinates;
    }

}
