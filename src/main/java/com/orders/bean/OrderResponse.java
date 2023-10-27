package com.orders.bean;

import com.orders.config.OrderUtils;

public class OrderResponse {

	private Long orderId;
	private double distance;

	private OrderUtils.OrderStatus status;

	public OrderResponse(Long orderId, double distance, OrderUtils.OrderStatus status) {
		this.orderId = orderId;
		this.distance = distance;
		this.status = status;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
}
