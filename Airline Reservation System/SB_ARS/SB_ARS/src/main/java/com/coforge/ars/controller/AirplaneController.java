package com.coforge.ars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coforge.ars.model.Airplane;
import com.coforge.ars.service.AirplaneService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/ars/airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    // Add Airplane
    @PostMapping
    public ResponseEntity<Airplane> addAirplane(@RequestBody Airplane airplane) {

        Airplane savedAirplane = airplaneService.addAirplane(airplane);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(savedAirplane);
    }

    // Get All Airplanes
    @GetMapping
    public ResponseEntity<List<Airplane>> getAllAirplanes() {

        return ResponseEntity.ok(
                airplaneService.getAllAirplanes());

    }

    // Get Airplane By Id
    @GetMapping("/{planeId}")
    public ResponseEntity<Airplane> getAirplaneById(
            @PathVariable Long planeId) {

        Airplane airplane =
                airplaneService.getAirplaneById(planeId);

        return ResponseEntity.ok(airplane);

    }

    // Update Airplane
    @PutMapping("/{planeId}")
    public ResponseEntity<Airplane> updateAirplane(
            @PathVariable Long planeId,
            @RequestBody Airplane airplane) {

        Airplane updatedAirplane =
                airplaneService.updateAirplane(planeId, airplane);

        return ResponseEntity.ok(updatedAirplane);

    }

    // Delete Airplane
    @DeleteMapping("/{planeId}")
    public ResponseEntity<String> deleteAirplane(
            @PathVariable Long planeId) {

        airplaneService.deleteAirplane(planeId);

        return ResponseEntity.ok("Airplane Deleted Successfully");

    }

}