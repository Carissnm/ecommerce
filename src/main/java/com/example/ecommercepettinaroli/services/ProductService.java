package com.example.ecommercepettinaroli.services;

import com.example.ecommercepettinaroli.models.Product;
import com.example.ecommercepettinaroli.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {


    ProductRepository productRepository;



    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> findProductByCode(Integer prdCode) {
        return productRepository.findProductByPrdCode(prdCode);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteProductByCode(Integer prdCode){
        productRepository.deleteProductByPrdCode(prdCode);
    }
}
