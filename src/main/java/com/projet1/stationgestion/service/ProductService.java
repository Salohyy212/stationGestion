package com.projet1.stationgestion.service;


import com.projet1.stationgestion.entity.Product;
import com.projet1.stationgestion.repository.ProductCrud;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private final ProductCrud productCrud;
    public ProductService(ProductCrud productCrud) {
        this.productCrud = productCrud;
    }
    public List<Product> getAllProducts()throws SQLException {
        return productCrud.findAll();
    }
    public Product saveProduct(Product product)throws SQLException {
        return productCrud.save(product);
    }
    public boolean updateProduct(int id, Product product) throws SQLException {
        return productCrud.update(product);
    }

}




