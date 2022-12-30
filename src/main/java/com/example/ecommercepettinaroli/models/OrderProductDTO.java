package com.example.ecommercepettinaroli.models;

import jakarta.validation.constraints.NotNull;

public class OrderProductDTO {

    private  Integer quantity;
    private  Long orderId;
    private  Long prdId;

    private Client client;

    public OrderProductDTO(Integer quantity, Long orderId, Long prdId, Client client) {
        this.quantity = quantity;
        this.orderId = orderId;
        this.prdId = prdId;
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPrdId() {
        return prdId;
    }

    public void setPrdId(Long prdId) {
        this.prdId = prdId;
    }

    @Override
    public String toString() {
        return "OrderProductDTO{" +
                "quantity=" + quantity +
                ", orderId=" + orderId +
                ", prdId=" + prdId +
                ", client=" + client +
                '}';
    }
}
