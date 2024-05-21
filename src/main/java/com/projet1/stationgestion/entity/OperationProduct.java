package com.projet1.stationgestion.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import java.time.Instant;


@Data
@AllArgsConstructor
public class StockMovement {
    private int id;
    private Product product;
    private Instant dateTime;
    private MovementType movementType;
    private int quantity;
}
