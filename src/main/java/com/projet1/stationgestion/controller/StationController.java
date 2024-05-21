package com.projet1.stationgestion.controller;

import com.projet1.stationgestion.entity.Station;
import com.projet1.stationgestion.service.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StationController {
    private StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/stations")
    public List<Station> getAllStations() throws SQLException {
        int pageSize = 10;
        return stationService.getAllStations();
    }
    @GetMapping("/station/{id}")
    public ResponseEntity<Station> getStationByNumber(@PathVariable int id) throws SQLException {
        Station station = stationService.getStationByNumber(id);
        if (station != null) {
            return new ResponseEntity<>(station, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/station")
    public ResponseEntity<Station> addStation(@RequestBody Station station) throws SQLException {
        Station savedStation = stationService.saveStation(station);
        return new ResponseEntity<>(savedStation, HttpStatus.CREATED);
    }
    @PutMapping("/station/{id}")
    public ResponseEntity<Void> updateStation(@PathVariable int id, @RequestBody Station stationUpdate) throws SQLException {
        boolean updateSuccess = StationService.updateStation(id, stationUpdate);
        if (updateSuccess) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

