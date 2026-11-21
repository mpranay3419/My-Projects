package com.coforge.ars.service;

import java.util.List;

import com.coforge.ars.model.Airport;

public interface AirportService {
	

    Airport addAirport(Airport airport);

    Airport updateAirport(Long airportId, Airport airport);

    void deleteAirport(Long airportId);

    Airport getAirportById(Long airportId);

    List<Airport> getAllAirports();

}
