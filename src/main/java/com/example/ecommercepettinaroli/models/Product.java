package com.example.ecommercepettinaroli.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUCTO")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRD_ID")
    private Long prdId;
    @Column(name = "PRD_COD")
    private @NotNull Integer prdCode;
    @Column(name = "PRD_DESCRP")
    private @NotNull String prdDescription;
    @Column(name = "PRD_CANTIDAD")
    private @NotNull Integer quantity;
    @Column(name = "PRD_PRECIO")
    private @NotNull Double prdPrice;

    public Product() {
    }

    public Product(Integer prdCode, String prdDescription, Integer quantity, Double prdPrice) {
        this.prdCode = prdCode;
        this.prdDescription = prdDescription;
        this.quantity = quantity;
        this.prdPrice = prdPrice;
    }

    public Long getPrdId() {
        return prdId;
    }

    public Integer getPrdCode() {
        return prdCode;
    }

    public void setPrdCode(Integer prdCode) {
        this.prdCode = prdCode;
    }

    public String getPrdDescription() {
        return prdDescription;
    }

    public void setPrdDescription(String prdDescription) {
        this.prdDescription = prdDescription;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrdPrice() {
        return prdPrice;
    }

    public void setPrdPrice(Double prdPrice) {
        this.prdPrice = prdPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prdId=" + prdId +
                ", prdCode=" + prdCode +
                ", prdDescription='" + prdDescription + '\'' +
                ", quantity=" + quantity +
                ", prdPrice=" + prdPrice +
                '}';
    }
}
