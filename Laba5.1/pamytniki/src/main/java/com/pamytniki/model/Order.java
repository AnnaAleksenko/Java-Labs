package com.pamytniki.model;


import java.util.Date;

public class Order {
    private String orderId;
    private String clientId;
    private Date orderDate;
    private String modelId;
    private String status;
    private Double totalPrice;

    public Order(String orderId, String clientId, Date orderDate,
                 String modelId, String status, Double totalPrice) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
        this.modelId = modelId;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    // Геттеры
    public String getOrderId() { return orderId; }
    public String getClientId() { return clientId; }
    public Date getOrderDate() { return orderDate; }
    public String getModelId() { return modelId; }
    public String getStatus() { return status; }
    public Double getTotalPrice() { return totalPrice; }

    // Сеттеры
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public void setModelId(String modelId) { this.modelId = modelId; }
    public void setStatus(String status) { this.status = status; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
}