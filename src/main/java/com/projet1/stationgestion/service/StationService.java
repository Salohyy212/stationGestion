package com.projet1.stationgestion.service;

import com.projet1.stationgestion.entity.Station;
import com.projet1.stationgestion.repository.StationCrud;

import java.sql.SQLException;
import java.util.List;

public class StationService {
    private static StationCrud stationCrud;
    public StationService(StationCrud stationCrud) {
        this.stationCrud = stationCrud;
    }
    public List<Station> getAllStations()throws SQLException {
        return stationCrud.findAll();
    }
    public Station saveStation(Station station)throws SQLException {
        return stationCrud.save(station);
    }
    public static boolean updateStation(int id, Station station) throws SQLException {
        return stationCrud.update(station);
    }
    public Station getStationByNumber(int id) {
        return stationCrud.getStationByNumber(id);
    }

}





