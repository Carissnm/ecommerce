package com.example.ecommercepettinaroli.controllers;

import com.example.ecommercepettinaroli.models.Product;
import com.example.ecommercepettinaroli.services.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductController {
    @Autowired
    private ProductService productService;



    @GetMapping(value = "/listar")
    public ResponseEntity<?> getAllProducts() {
        try {
            Iterable<Product> products =  productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(name = "id") Long id) {
        try {
            Optional<Product> prd = productService.findProductById(id);
            if(prd.isPresent()) {
                return ResponseEntity.ok(prd);
            } else {
                return new ResponseEntity<>("No se encontró el producto solicitado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            Optional<Product> prdCheck = productService.findProductByCode(product.getPrdCode());
            if(prdCheck.isEmpty()) {
                Product savedProduct = productService.saveOrUpdate(product);
                return ResponseEntity.ok(savedProduct);
            } else {
                return new ResponseEntity<>("El producto ya se encuentra registrado", HttpStatus.CONFLICT);
            }
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }

    @DeleteMapping(value = "/borrar/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable(name = "id") Long id) {
        try {
            Optional<Product> prdDelete = productService.findProductById(id);
            if(prdDelete.isPresent()) {
                productService.deleteProductById(id);
                return ResponseEntity.ok("El producto fue eliminado correctamente");
            } else {
                return new ResponseEntity<>("El producto no existe", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }

    @DeleteMapping(value = "/borrar/{prdCode}")
    public ResponseEntity<?> deleteProductByPrdCode(@PathVariable(name = "prdCode") Integer prdCode) {
        try {
            Optional<Product> prdDelete = productService.findProductByCode(prdCode);
            if(prdDelete.isPresent()) {
                productService.deleteProductByCode(prdCode);
                return ResponseEntity.ok("El producto de código " + prdCode + "fue eliminado satisfactoriamente");
            } else {
                return new ResponseEntity<>("El producto seleccionado no existe", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }

    @PutMapping(value = "/modificar/{id}")
    public ResponseEntity<?> updateProduct (@PathVariable(name = "id") Long id, @RequestBody Product product) {
        try {
            Optional<Product> prdUpdate = productService.findProductById(id);
            if(prdUpdate.isPresent()) {
                Product updatePrd = prdUpdate.get();
                updatePrd.setPrdCode(product.getPrdCode());
                updatePrd.setPrdDescription(product.getPrdDescription());
                updatePrd.setQuantity(product.getQuantity());
                updatePrd.setPrdPrice(product.getPrdPrice());

                return new ResponseEntity<>(productService.saveOrUpdate(updatePrd), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("El cliente no existe", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }


}
