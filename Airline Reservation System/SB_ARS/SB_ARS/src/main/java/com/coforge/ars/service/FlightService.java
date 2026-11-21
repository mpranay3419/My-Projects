package com.coforge.ars.service;

import java.util.List;

import com.coforge.ars.model.Flight;

public interface FlightService {

    Flight addFlight(Flight flight);

    Flight updateFlight(Long flightId, Flight flight);

    void deleteFlight(Long flightId);

    Flight getFlightById(Long flightId);

    List<Flight> getAllFlights();
    
    List<Flight> searchFlights(Long sourceAirportId,
            Long destinationAirportId);
    
    Flight updateFlightStatus(Long flightId, String status);

}