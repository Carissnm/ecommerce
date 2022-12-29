package com.example.ecommercepettinaroli.services;

import com.example.ecommercepettinaroli.repositories.OrderProductRepository;
import com.example.ecommercepettinaroli.repositories.OrderRepository;
import com.example.ecommercepettinaroli.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderProductService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderProductRepository repository;

    @Autowired
    public OrderProductService(OrderRepository orderRepository, ProductRepository productRepository, OrderProductRepository repository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.repository = repository;
    }
}
