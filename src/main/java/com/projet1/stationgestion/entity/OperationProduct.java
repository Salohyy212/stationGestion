package com.projet1.stationgestion.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import java.time.Instant;
import java.util.List;


@Data
@AllArgsConstructor
public class OperationProduct {
    private int id;
    List <Product> products;
    private Instant dateTime;
    private OperationType operationType;
    private int quantity;

    public OperationProduct(int id, Instant dateTime, String operationType, int quantity){
        this.id=id;
        this.dateTime=dateTime;
        this.operationType= OperationType.valueOf(operationType);
        this.quantity=quantity;
    }

    public OperationProduct() {

    }
}
