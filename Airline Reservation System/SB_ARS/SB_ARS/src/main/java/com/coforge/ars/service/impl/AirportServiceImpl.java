package com.coforge.ars.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.coforge.ars.exception.AirportNotFoundException;
import com.coforge.ars.model.Airport;
import com.coforge.ars.repo.AirportRepo;
import com.coforge.ars.service.AirportService;

@Service
public class AirportServiceImpl implements AirportService {

    private AirportRepo repo;
    private Environment environment;

    @Autowired
    public AirportServiceImpl(AirportRepo repo,
                              Environment environment) {

        this.repo = repo;
        this.environment = environment;
    }

    @Override
    public Airport addAirport(Airport airport) {

        return repo.save(airport);

    }

    @Override
    public Airport updateAirport(Long airportId,
                                 Airport airport) {

        if (!repo.existsById(airportId)) {
            throw new AirportNotFoundException(
                    environment.getProperty("ars.invalid.airport-notfound"));
        }

        airport.setAirportId(airportId);

        return repo.save(airport);
    }

    @Override
    public void deleteAirport(Long airportId) {

        if (!repo.existsById(airportId)) {
            throw new AirportNotFoundException(
                    environment.getProperty("ars.invalid.airport-notfound"));
        }

        repo.deleteById(airportId);
    }

    @Override
    public Airport getAirportById(Long airportId) {

        if (!repo.existsById(airportId)) {
            throw new AirportNotFoundException(
                    environment.getProperty("ars.invalid.airport-notfound"));
        }

        Optional<Airport> airport = repo.findById(airportId);

        return airport.get();
    }

    @Override
    public List<Airport> getAllAirports() {

        return repo.findAll();

    }

}