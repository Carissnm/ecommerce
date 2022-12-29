package com.example.ecommercepettinaroli.repositories;

import com.example.ecommercepettinaroli.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<Product> findProductByPrdCode(Integer prdCode);

    public void deleteProductByPrdCode(Integer prdCode);
}
