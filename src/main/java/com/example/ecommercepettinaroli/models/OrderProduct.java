package com.example.ecommercepettinaroli.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Or;

import java.io.Serializable;

@Entity
@Table(name = "ORDEN_PRODUCTO")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "CANTIDAD")
    private @NotNull Integer quantity;

    @Column(name = "TOTAL_PARCIAL")
    private @NotNull Double lineTotal;

    //Constructor default

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PRD_ID")
    private Product product;



    public OrderProduct() {
    }

    public OrderProduct(Integer quantity, Order order, Product product) {
        this.quantity = quantity;
        this.lineTotal = 0.00;
        this.order = order;
        this.product = product;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(Double lineTotal) {
        this.lineTotal = lineTotal;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", lineTotal=" + lineTotal +
                ", product=" + product +
                '}';
    }
}
