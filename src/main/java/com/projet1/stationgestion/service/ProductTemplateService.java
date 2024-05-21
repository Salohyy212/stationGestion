package com.projet1.stationgestion.service;


import com.projet1.stationgestion.entity.ProductTemplate;
import com.projet1.stationgestion.repository.ProductTemplateCrud;

import java.sql.SQLException;
import java.util.List;

public class ProductTemplateService {
    private final ProductTemplateCrud productTemplateCrud;
    public ProductTemplateService(ProductTemplateCrud productTemplateCrud) {
        this.productTemplateCrud = productTemplateCrud;
    }
    public List<ProductTemplate> getAllProductTemplates()throws SQLException {
        return productTemplateCrud.findAll();
    }
    public ProductTemplate saveProductTemplate(ProductTemplate productTemplate)throws SQLException {
        return productTemplateCrud.save(productTemplate);
    }
    public boolean updateProductTemplate(int id, ProductTemplate productTemplate) throws SQLException {
        return productTemplateCrud.update(productTemplate);
    }

}




