package com.example.ecommercepettinaroli.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLIENTE")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CL_ID", columnDefinition = "integer(11)")
    private Long clientId;

    @Column(name = "CL_NOMBRE", columnDefinition = "varchar(255)")
    private String name;
    @Column(name = "CL_SURNAME", columnDefinition = "varchar(255)")
    private String surname;
    @Column(name = "CL_DNI", columnDefinition = "varchar(255)")
    private String dni;

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Receipt> receipts;

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Client() {
    }

    public Client(String name, String surname, String dni) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.receipts = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dni='" + dni + '\'' +
                ", receipts=" + receipts +
                ", orders=" + orders +
                '}';
    }
}
