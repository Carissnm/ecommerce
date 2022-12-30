package com.example.ecommercepettinaroli.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDEN")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDEN_ID")
    private Long orderId;

    @Column(name = "FECHA")
    private LocalDate dateCreated;

    @Column(name = "ESTADO")
    private OrderStatus orderStatus;

    @JsonManagedReference
    @OneToMany(mappedBy = "id", cascade=CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "CL_ID")
    private Client client;

    public Order() {
    }

    public Order(LocalDate dateCreated, Client client) {
        this.dateCreated = dateCreated;
        this.client = client;
        this.orderStatus = OrderStatus.PENDIENTE;
        this.orderProducts = new ArrayList<>();
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", dateCreated=" + dateCreated +
                ", orderProducts=" + orderProducts +
                ", client=" + client +
                '}';
    }
}
