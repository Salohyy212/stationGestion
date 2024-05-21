package com.projet1.stationgestion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductTemplate {
    private int id;
    private ProductName productName;
    private double Price;
}

