package com.example.ecommercepettinaroli.services;

import com.example.ecommercepettinaroli.models.Order;
import com.example.ecommercepettinaroli.models.OrderProduct;
import com.example.ecommercepettinaroli.models.Product;
import com.example.ecommercepettinaroli.repositories.OrderProductRepository;
import com.example.ecommercepettinaroli.repositories.OrderRepository;
import com.example.ecommercepettinaroli.repositories.ProductRepository;
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

    private OrderRepository orderRepository;

    private ProductRepository productRepository;

    private OrderProductRepository orderProductRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository) {
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

    public ResponseEntity<?> addLine(Long orderId, Long prdId, Integer quantity) {
        try {
            Optional<Order> order = orderRepository.findById(orderId);
            Optional<Product> prd = productRepository.findById(prdId);
            if(prd.isPresent() && stockCheck(prdId)) {
                if(quantity >= getStock(prdId)) {
                    if(order.isPresent()) {
                        OrderProduct newLine = new OrderProduct(quantity, order.get(), prd.get());
                        order.get().getOrderProducts().add(newLine);
                        return ResponseEntity.ok(order);
                    } else {
                        Order newOrder = new Order();
                        OrderProduct newLine = new OrderProduct(quantity, newOrder, prd.get());
                        newOrder.getOrderProducts().add(newLine);
                        return ResponseEntity.ok(newOrder);
                    }
                } else {
                    return new ResponseEntity<>("No se encontró el producto solicitado", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("No hay suficiente stock del producto solicitado", HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }

    private Boolean stockCheck(Long id) {
        return productRepository.findById(id).get().getQuantity() > 0;
    }

    private Integer getStock(Long id) {
        return productRepository.findById(id).get().getQuantity();
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
