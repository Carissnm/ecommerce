package com.example.ecommercepettinaroli.services;

import com.example.ecommercepettinaroli.models.Order;
import com.example.ecommercepettinaroli.models.OrderProduct;
import com.example.ecommercepettinaroli.models.OrderProductDTO;
import com.example.ecommercepettinaroli.models.Product;
import com.example.ecommercepettinaroli.repositories.OrderProductRepository;
import com.example.ecommercepettinaroli.repositories.OrderRepository;
import com.example.ecommercepettinaroli.repositories.ProductRepository;
import com.example.ecommercepettinaroli.repositories.ReceiptRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService implements Serializable {

    private ReceiptRepository receiptRepository;

    private OrderRepository orderRepository;

    private ProductRepository productRepository;

    private OrderProductRepository orderProductRepository;

    @Autowired
    public OrderService(ReceiptRepository receiptRepository, OrderRepository orderRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository) {
        this.receiptRepository = receiptRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
    }




    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrUpdate(Order order) {
        return orderRepository.save(order);
    }

    public OrderProduct createLine(OrderProductDTO orderProductDTO) {
        Optional<Product> product = productRepository.findById(orderProductDTO.getPrdId());
        Optional<Order> order = orderRepository.findById(orderProductDTO.getOrderId());
        if(product.isPresent() && orderProductDTO.getQuantity() <= getStock(product.get().getPrdId()) && order.isPresent()) {
            OrderProduct line = new OrderProduct(orderProductDTO.getQuantity(), order.get(), product.get());
            line.setLineTotal(partialTotal(line));
            return orderProductRepository.save(line);
        } else {

            return null;
        }
    }

    public void addLine(Long orderId, Long lineId) {
        Optional<Order> order = orderRepository.findById(orderId);
        Optional<OrderProduct> line = orderProductRepository.findById(lineId);
        if (order.isPresent() && line.isPresent()){
            order.get().getOrderProducts().add(line.get());
            orderRepository.save(order.get());
        }
    }

    private Integer getStock(Long id) {
        Optional<Product> prd = productRepository.findById(id) ;
        if(prd.isPresent()) {
            return prd.get().getQuantity();
        } else {
            return 0;
        }

    }

    private Double partialTotal(OrderProduct line) {
        return line.getQuantity() * line.getProduct().getPrdPrice();
    }

    private Double calculateTotal(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        Double sum = 0D;
        if(order.isPresent()) {
            List<OrderProduct> orderLines = order.get().getOrderProducts();
            for(OrderProduct orderLine : orderLines) {
                sum += orderLine.getQuantity() * orderLine.getProduct().getPrdPrice();
            }
            return sum;
        } else {
            return 0.00;
        }

    }
}
