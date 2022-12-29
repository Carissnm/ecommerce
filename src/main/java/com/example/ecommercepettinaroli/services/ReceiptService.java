package com.example.ecommercepettinaroli.services;

import com.example.ecommercepettinaroli.repositories.ClientRepository;
import com.example.ecommercepettinaroli.repositories.OrderRepository;
import com.example.ecommercepettinaroli.repositories.ReceiptRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@Transactional
public class ReceiptService implements Serializable {

    private OrderRepository orderRepository;

    private ClientRepository clientRepository;
    private ReceiptRepository receiptRepository;

    @Autowired

    public ReceiptService(OrderRepository orderRepository, ClientRepository clientRepository, ReceiptRepository receiptRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.receiptRepository = receiptRepository;
    }
}
