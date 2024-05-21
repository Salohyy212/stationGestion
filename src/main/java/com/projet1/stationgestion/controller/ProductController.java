package com.projet1.stationgestion.controller;

import com.projet1.stationgestion.entity.Product;
import com.projet1.stationgestion.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() throws SQLException {
        int pageSize = 10;
        return productService.getAllProducts();
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductByNumber(@PathVariable int id) throws SQLException {
        Product product = productService.getProductByNumber(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws SQLException {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable int id, @RequestBody Product productUpdate) throws SQLException {
        boolean updateSuccess = ProductService.updateProduct(id, productUpdate);
        if (updateSuccess) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


