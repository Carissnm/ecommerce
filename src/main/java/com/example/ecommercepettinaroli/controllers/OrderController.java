package com.example.ecommercepettinaroli.controllers;

import com.example.ecommercepettinaroli.models.Client;
import com.example.ecommercepettinaroli.models.Order;
import com.example.ecommercepettinaroli.models.OrderProduct;
import com.example.ecommercepettinaroli.models.OrderProductDTO;
import com.example.ecommercepettinaroli.services.OrderProductService;
import com.example.ecommercepettinaroli.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orden")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOrder(@PathVariable(name = "id") Long id) {
        try {
            Optional<Order> ord = orderService.getOrder(id);
            if(ord.isPresent()) {
                return ResponseEntity.ok(ord);
            } else {
                return new ResponseEntity<>("Orden no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> createNewOrder(@RequestBody Client client) {
        try {
            Order newOrder = new Order(LocalDate.now(), client);
            Order savedOrder = orderService.saveOrUpdate(newOrder);
            return ResponseEntity.ok(savedOrder);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }


    @PutMapping(value = "/agregar-item")
    public ResponseEntity<?> addItem(@RequestBody OrderProductDTO orderProductDTO) {
        try {
            Order order = orderService.createLine(orderProductDTO);
            return ResponseEntity.ok(order);
      } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }


}
