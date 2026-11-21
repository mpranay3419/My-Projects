package com.coforge.ars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coforge.ars.model.Airport;
import com.coforge.ars.service.AirportService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/ars/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    // Add Airport
    @PostMapping
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport) {

        Airport savedAirport = airportService.addAirport(airport);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(savedAirport);
    }

    // Get All Airports
    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {

        return ResponseEntity.ok(
                airportService.getAllAirports());

    }

    // Get Airport By Id
    @GetMapping("/{airportId}")
    public ResponseEntity<Airport> getAirportById(
            @PathVariable Long airportId) {

        Airport airport = airportService.getAirportById(airportId);

        return ResponseEntity.ok(airport);

    }

    // Update Airport
    @PutMapping("/{airportId}")
    public ResponseEntity<Airport> updateAirport(
            @PathVariable Long airportId,
            @RequestBody Airport airport) {

        Airport updatedAirport =
                airportService.updateAirport(airportId, airport);

        return ResponseEntity.ok(updatedAirport);

    }

    // Delete Airport
    @DeleteMapping("/{airportId}")
    public ResponseEntity<String> deleteAirport(
            @PathVariable Long airportId) {

        airportService.deleteAirport(airportId);

        return ResponseEntity.ok("Airport Deleted Successfully");

    }

}