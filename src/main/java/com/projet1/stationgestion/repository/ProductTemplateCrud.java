package com.projet1.stationgestion.repository;

import com.projet1.stationgestion.db.ConnectDB;
import com.projet1.stationgestion.entity.Product;
import com.projet1.stationgestion.entity.ProductName;
import com.projet1.stationgestion.entity.ProductTemplate;
import com.projet1.stationgestion.entity.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductTemplateCrud implements CrudOperations<ProductTemplate>{
    private final ConnectDB db = ConnectDB.getInstance();
    private Connection connection = db.getConnection();

    @Override
    public List<ProductTemplate> findAll() {
        List<ProductTemplate> productTemplateList = new ArrayList<>();
        String sql = "SELECT * FROM productTemplate";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                ProductTemplate productTemplate = new ProductTemplate(
                        resultSet.getInt("productTemplateId"),
                        resultSet.getObject("productName", ProductName.class),
                        resultSet.getDouble("price")
                );
                productTemplateList.add(productTemplate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  productTemplateList;
    }

    @Override
    public ProductTemplate save(ProductTemplate toSave) {
        String insertQuery = "INSERT INTO productTemplate (id, productName,price) VALUES (?,?,?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setInt(1, toSave.getId());
            insertStatement.setString(2, String.valueOf(toSave.getProductName()));
            insertStatement.setDouble(3,toSave.getPrice());
            int rowsAffected = insertStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    toSave.setId(generatedKeys.getInt(1));
                    return toSave;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean update(ProductTemplate toSave) {
        String updateQuery = "UPDATE productTemplate SET id=?, productName=?, price=? WHERE id=?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, toSave.getId());
            updateStatement.setString(2, String.valueOf(toSave.getProductName()));
            updateStatement.setDouble(3,toSave.getPrice());
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public ProductTemplate getProductTemplateByNumber(int id) {
        String sql = "SELECT * FROM productTemplate WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new ProductTemplate(
                        resultSet.getInt("productTemplateId"),
                        resultSet.getObject("productName", ProductName.class),
                        resultSet.getDouble("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
