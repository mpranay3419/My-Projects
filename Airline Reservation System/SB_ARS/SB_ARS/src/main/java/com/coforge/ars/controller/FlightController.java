package com.coforge.ars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coforge.ars.model.Flight;
import com.coforge.ars.service.FlightService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/ars/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    // Add Flight
    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {

        Flight savedFlight = flightService.addFlight(flight);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedFlight);
    }

    // Get All Flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {

        return ResponseEntity.ok(
                flightService.getAllFlights());

    }

    // Get Flight By Id
    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightById(
            @PathVariable Long flightId) {

        Flight flight = flightService.getFlightById(flightId);

        return ResponseEntity.ok(flight);

    }

    // Update Flight
    @PutMapping("/{flightId}")
    public ResponseEntity<Flight> updateFlight(
            @PathVariable Long flightId,
            @RequestBody Flight flight) {

        Flight updatedFlight =
                flightService.updateFlight(flightId, flight);

        return ResponseEntity.ok(updatedFlight);

    }

    // Delete Flight
    @DeleteMapping("/{flightId}")
    public ResponseEntity<String> deleteFlight(
            @PathVariable Long flightId) {

        flightService.deleteFlight(flightId);

        return ResponseEntity.ok("Flight Deleted Successfully");

    }

    // Search Flights
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam Long sourceAirportId,
            @RequestParam Long destinationAirportId) {

        List<Flight> flights =
                flightService.searchFlights(
                        sourceAirportId,
                        destinationAirportId);

        return ResponseEntity.ok(flights);

    }

    // Update Flight Status
    @PutMapping("/{flightId}/status")
    public ResponseEntity<Flight> updateFlightStatus(
            @PathVariable Long flightId,
            @RequestParam String status) {

        Flight flight =
                flightService.updateFlightStatus(flightId, status);

        return ResponseEntity.ok(flight);

    }

}