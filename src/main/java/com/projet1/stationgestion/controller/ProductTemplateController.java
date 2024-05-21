package com.projet1.stationgestion.controller;

import com.projet1.stationgestion.entity.ProductTemplate;
import com.projet1.stationgestion.service.ProductTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductTemplateController {
    private ProductTemplateService productTemplateService;

    public ProductTemplateController(ProductTemplateService productTemplateService) {
        this.productTemplateService = productTemplateService;
    }

    @GetMapping("/productTemplates")
    public List<ProductTemplate> getAllProductTemplates() throws SQLException {
        int pageSize = 10;
        return productTemplateService.getAllProductTemplates();
    }
    @GetMapping("/productTemplate/{id}")
    public ResponseEntity<ProductTemplate> getProductTemplateByNumber(@PathVariable int id) throws SQLException {
        ProductTemplate productTemplate = productTemplateService.getProductTemplateByNumber(id);
        if (productTemplate != null) {
            return new ResponseEntity<>(productTemplate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/productTemplate")
    public ResponseEntity<ProductTemplate> addProductTemplate(@RequestBody ProductTemplate productTemplate) throws SQLException {
        ProductTemplate savedProductTemplate = productTemplateService.saveProductTemplate(productTemplate);
        return new ResponseEntity<>(savedProductTemplate, HttpStatus.CREATED);
    }
    @PutMapping("/productTemplate/{id}")
    public ResponseEntity<Void> updateProductTemplate(@PathVariable int id, @RequestBody ProductTemplate productTemplateUpdate) throws SQLException {
        boolean updateSuccess = ProductTemplateService.updateProductTemplate(id, productTemplateUpdate);
        if (updateSuccess) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


