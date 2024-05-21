package com.projet1.stationgestion.controller;

import com.projet1.stationgestion.entity.OperationProduct;
import com.projet1.stationgestion.entity.Product;
import com.projet1.stationgestion.service.OperationProductService;
import com.projet1.stationgestion.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OperationProductController {
    private OperationProductService operationProductService;

    public OperationProductController(OperationProductService operationProductService) {
        this.operationProductService = operationProductService;
    }

    @GetMapping("/operationProducts")
    public List<OperationProduct> getAllOperationProducts() throws SQLException {
        int pageSize = 10;
        return operationProductService.getAllOperationProducts();
    }
    @GetMapping("/opeationProduct/{id}")
    public ResponseEntity<OperationProduct> getOperationProductByNumber(@PathVariable int id) throws SQLException {
        OperationProduct operationProduct = operationProductService.getOperationProductByNumber(id);
        if (operationProduct != null) {
            return new ResponseEntity<>(operationProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/operationProduct")
    public ResponseEntity<OperationProduct> addOperationProduct(@RequestBody OperationProduct operationProduct) throws SQLException {
        OperationProduct savedOperationProduct = operationProductService.saveOperationProduct(operationProduct);
        return new ResponseEntity<>(savedOperationProduct, HttpStatus.CREATED);
    }
    @PutMapping("/operationProduct/{id}")
    public ResponseEntity<Void> updateOperationProduct(@PathVariable int id, @RequestBody OperationProduct operationProductUpdate) throws SQLException {
        boolean updateSuccess = OperationProductService.updateOperationProduct(id, operationProductUpdate);
        if (updateSuccess) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
