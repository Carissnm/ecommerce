package com.example.ecommercepettinaroli.repositories;

import com.example.ecommercepettinaroli.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
