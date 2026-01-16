package com.project.dto;

import java.util.List;

public class OrderResponseDto {

    private long orderId;
    private String status;
    private double orderPrice;
    private double totalAmount;
    private List<OrderItemResponseDto> orderItems;

    public OrderResponseDto() {
    }

    public OrderResponseDto(long orderId, String status, double orderPrice, double totalAmount, List<OrderItemResponseDto> orderItems) {
        this.orderId = orderId;
        this.status = status;
        this.orderPrice = orderPrice;
        this.totalAmount = totalAmount;
        this.orderItems = orderItems;
    }

    public OrderResponseDto(String status, double orderPrice, double totalAmount, List<OrderItemResponseDto> orderItems) {
        this.status = status;
        this.orderPrice = orderPrice;
        this.totalAmount = totalAmount;
        this.orderItems = orderItems;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderItemResponseDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemResponseDto> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderResponseDto {" +
                "orderId=" + orderId +
                ", status='" + status + '\'' +
                ", orderPrice=" + orderPrice +
                ", totalAmount=" + totalAmount +
                ", orderItems=" + orderItems +
                '}';
    }
}
