package com.projet1.stationgestion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;
import java.util.List;
@Data
@AllArgsConstructor
public class Station {
    private int id;
    private String location;
    List<Product> products;
    private List<OperationProduct> operationProducts;

    public Station(int id, String location){
        this.id=id;
        this.location=location;
    }

    public void supply(Product product, double quantity){
        for(Product Product: products){
            if(Product.getId() == product.getId()){
                Product.setStockQuantity(Product.getStockQuantity()+quantity);
            }
        }
        product.setStockQuantity(quantity);
        products.add(product);
    }
    public boolean sell(Product product, double quantity) {
        for (Product Product : products) {
            if (Product.getId() == product.getId()) {
                if (Product.getStockQuantity() >= quantity) {
                    Product.setStockQuantity(Product.getStockQuantity() - quantity);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    public double getRestantStock(Product product, Instant dateTime){
        OperationProduct OperationProduct = new OperationProduct();
        double restantStock = 0;
        for(OperationProduct operationProduct : operationProducts){
            if(operationProduct.getProducts().equals(product) && operationProduct.getDateTime().compareTo(dateTime)<=0){
                if(operationProduct.getOperationType().equals("Entry")){
                    restantStock += OperationProduct.getQuantity();
                }else if(operationProduct.getOperationType().equals("Exit")){
                    restantStock -= OperationProduct.getQuantity();
                }
            }
        }
        return restantStock;
    }

}
