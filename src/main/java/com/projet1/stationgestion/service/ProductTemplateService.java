package com.projet1.stationgestion.service;


import com.projet1.stationgestion.entity.ProductTemplate;
import com.projet1.stationgestion.repository.ProductTemplateCrud;

import java.sql.SQLException;
import java.util.List;

public class ProductTemplateService {
    private static  ProductTemplateCrud productTemplateCrud;
    public ProductTemplateService(ProductTemplateCrud productTemplateCrud) {
        this.productTemplateCrud = productTemplateCrud;
    }
    public List<ProductTemplate> getAllProductTemplates()throws SQLException {
        return productTemplateCrud.findAll();
    }
    public ProductTemplate saveProductTemplate(ProductTemplate productTemplate)throws SQLException {
        return productTemplateCrud.save(productTemplate);
    }
    public static boolean updateProductTemplate(int id, ProductTemplate productTemplate) throws SQLException {
        return productTemplateCrud.update(productTemplate);
    }
    public ProductTemplate getProductTemplateByNumber(int id) {
        return productTemplateCrud.getProductTemplateByNumber(id);
    }

}




