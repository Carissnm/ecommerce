package com.example.ecommercepettinaroli.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "RECIBO")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECIBO_ID")
    private Long rcpId;
    @Column(name = "FECHA")
    private LocalDate date;
    @Column(name = "TOTAL")
    private Double total;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENTE_ID", columnDefinition = "integer(11)")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDEN_ID", referencedColumnName = "ORDEN_ID")
    private Order order;

    public Receipt() {
    }

    public Receipt(LocalDate date, Double total, Client client, Order order) {
        this.date = date;
        this.total = total;
        this.client = client;
        this.order = order;
    }

    public Long getRcpId() {
        return rcpId;
    }

    public void setRcpId(Long rcpId) {
        this.rcpId = rcpId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


}
