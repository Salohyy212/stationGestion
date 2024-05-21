package com.projet1.stationgestion.repository;

import com.projet1.stationgestion.db.ConnectDB;
import com.projet1.stationgestion.entity.OperationProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OperationProductCrud implements CrudOperations<OperationProduct>{
    private final ConnectDB db = ConnectDB.getInstance();
    private Connection connection = db.getConnection();

    @Override
    public List<OperationProduct> findAll() {
        List<OperationProduct> operationProductList = new ArrayList<>();
        String sql = "SELECT * FROM operationProduct";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                OperationProduct operationProduct = new OperationProduct(
                        resultSet.getInt("productId"),
                        resultSet.getTimestamp("dateTime").toInstant(),
                        resultSet.getString("operationType"),
                        resultSet.getInt("quantity")
                );
                operationProductList.add(operationProduct);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  operationProductList;
    }

    @Override
    public OperationProduct save(OperationProduct toSave) {
        String insertQuery = "INSERT INTO product (id, idTemplate,stockQuantity) VALUES (?,?,?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setInt(1, toSave.getId());
            insertStatement.setTimestamp(2, Timestamp.from(toSave.getDateTime()));
            insertStatement.setString(3, String.valueOf(toSave.getOperationType()));
            insertStatement.setInt(4, toSave.getQuantity());

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
    public boolean update(OperationProduct toSave) {
        String updateQuery = "UPDATE operationProduct SET id=?, dateTime=?, operationType=?, quantity=? WHERE id=?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, toSave.getId());
            updateStatement.setTimestamp(2, Timestamp.from(toSave.getDateTime()));
            updateStatement.setString(3,String.valueOf(toSave.getOperationType()));
            updateStatement.setInt(4, toSave.getQuantity());
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
