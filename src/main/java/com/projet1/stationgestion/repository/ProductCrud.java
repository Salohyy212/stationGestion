package com.projet1.stationgestion.repository;

import com.projet1.stationgestion.db.ConnectDB;
import com.projet1.stationgestion.entity.Product;
import com.projet1.stationgestion.entity.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCrud implements CrudOperations<Product>{
    private final ConnectDB db = ConnectDB.getInstance();
    private Connection connection = db.getConnection();

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("idTemplate"),
                        resultSet.getDouble("stockQuantity"),
                        resultSet.getInt("evaporationRate")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  productList;
    }

    @Override
    public Product save(Product toSave) {
        String insertQuery = "INSERT INTO product (idTemplate,stockQuantity, evaporationRate) VALUES (?,?,?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setInt(1, toSave.getIdTemplate());
            insertStatement.setDouble(2,toSave.getStockQuantity());
            insertStatement.setInt(3, toSave.getEvaporationRate());
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
    public boolean update(Product toSave) {
        String updateQuery = "UPDATE product SET id=?, idTemplate=?, stockQuantity=?, evaporationRate=? WHERE id=?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, toSave.getIdTemplate());
            updateStatement.setDouble(2,toSave.getStockQuantity());
            updateStatement.setInt(3, toSave.getEvaporationRate());
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public Product getProductByNumber(int id) {
        String sql = "SELECT * FROM product WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Product(
                        resultSet.getInt("idTemplate"),
                        resultSet.getDouble("stockQuantity"),
                        resultSet.getInt("evaporationRate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
