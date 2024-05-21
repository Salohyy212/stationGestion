package com.projet1.stationgestion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Product {
    private int id;
    private int idTemplate;
    private double stockQuantity;
    private int evaporationRate;

    public Product(int id, double stockQuantity, int evaporationRate){
        this.id=id;
        this.stockQuantity=stockQuantity;
        this.evaporationRate=evaporationRate;
    }


}
