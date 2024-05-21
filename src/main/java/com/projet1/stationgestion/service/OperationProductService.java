package com.projet1.stationgestion.service;

import com.projet1.stationgestion.entity.OperationProduct;
import com.projet1.stationgestion.repository.OperationProductCrud;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class OperationProductService {
    private final OperationProductCrud operationProductCrud;
    public OperationProductService(OperationProductCrud operationProductCrud) {
        this.operationProductCrud = operationProductCrud;
    }


    public List<OperationProduct> getAllOperationProducts()throws SQLException {
        return operationProductCrud.findAll();
    }
    public OperationProduct saveOperationProduct(OperationProduct operationProduct)throws SQLException {
        return operationProductCrud.save(operationProduct);
    }
    public boolean updateOperationProduct(int id, OperationProduct operationProduct) throws SQLException {
        return operationProductCrud.update(operationProduct);
    }

}


