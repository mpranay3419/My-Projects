package com.coforge.ars.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.coforge.ars.exception.FlightNotFoundException;
import com.coforge.ars.model.Airplane;
import com.coforge.ars.model.Flight;
import com.coforge.ars.repo.AirplaneRepo;
import com.coforge.ars.repo.FlightRepo;
import com.coforge.ars.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepo repo;
    private Environment environment;
    
    @Autowired
    private AirplaneRepo airplaneRepo;	

    @Autowired
    public FlightServiceImpl(FlightRepo repo,
                             Environment environment) {

        this.repo = repo;
        this.environment = environment;
    }

    @Override
    public Flight addFlight(Flight flight) {

    	Airplane airplane = airplaneRepo.findById(
                flight.getAirplane().getPlaneId())
                .orElseThrow(() ->
                new RuntimeException("Airplane Not Found"));

        flight.setAirplane(airplane);

        flight.setAvailableSeats(airplane.getCapacity());

        return repo.save(flight);
    }

    @Override
    public Flight updateFlight(Long flightId,
                               Flight flight) {

        if (!repo.existsById(flightId)) {
            throw new FlightNotFoundException(
                    environment.getProperty("ars.invalid.flight-notfound"));
        }

        Flight existingFlight = repo.findById(flightId).get();

        flight.setFlightId(flightId);

        // Preserve current available seats
        flight.setAvailableSeats(
                existingFlight.getAvailableSeats());

        return repo.save(flight);
    }

    @Override
    public void deleteFlight(Long flightId) {

        if (!repo.existsById(flightId)) {
            throw new FlightNotFoundException(
                    environment.getProperty("ars.invalid.flight-notfound"));
        }

        repo.deleteById(flightId);
    }

    @Override
    public Flight getFlightById(Long flightId) {

        if (!repo.existsById(flightId)) {
            throw new FlightNotFoundException(
                    environment.getProperty("ars.invalid.flight-notfound"));
        }

        Optional<Flight> flight = repo.findById(flightId);

        return flight.get();
    }

    @Override
    public List<Flight> getAllFlights() {

        return repo.findAll();

    }

    @Override
    public List<Flight> searchFlights(Long sourceAirportId,
                                      Long destinationAirportId) {

        List<Flight> flights =
                repo.findBySourceAirportAirportIdAndDestinationAirportAirportId(
                        sourceAirportId,
                        destinationAirportId);

        if (flights.isEmpty()) {
            throw new FlightNotFoundException(
                    environment.getProperty("ars.invalid.flight-notfound"));
        }

        return flights;
    }

    @Override
    public Flight updateFlightStatus(Long flightId,
                                     String status) {

        if (!repo.existsById(flightId)) {
            throw new FlightNotFoundException(
                    environment.getProperty("ars.invalid.flight-notfound"));
        }

        Flight flight = repo.findById(flightId).get();

        flight.setStatus(status);

        return repo.save(flight);
    }

}