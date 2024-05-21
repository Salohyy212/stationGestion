package com.projet1.stationgestion.repository;

import com.projet1.stationgestion.db.ConnectDB;
import com.projet1.stationgestion.entity.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationCrud implements CrudOperations<Station>{
    private final ConnectDB db = ConnectDB.getInstance();
    private Connection connection = db.getConnection();

    @Override
    public List<Station> findAll() {
        List<Station> stationList = new ArrayList<>();
        String sql = "SELECT * FROM station";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Station station = new Station(
                        resultSet.getInt("stationId"),
                        resultSet.getString("location")
                );
                stationList.add(station);
            }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  stationList;
    }

    @Override
    public Station save(Station toSave) {
        String insertQuery = "INSERT INTO station (id, location) VALUES (?,?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setInt(1, toSave.getId());
            insertStatement.setString(2, toSave.getLocation());

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
    public boolean update(Station toSave) {
        String updateQuery = "UPDATE station SET id=?, localisation=? WHERE id=?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, toSave.getId());
            updateStatement.setString(2, toSave.getLocation());
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public Station getStationByNumber(int id) {
        String sql = "SELECT * FROM station WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Station(
                        resultSet.getInt("stationId"),
                        resultSet.getString("location")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
