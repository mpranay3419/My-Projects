package com.coforge.ars.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.coforge.ars.exception.AirplaneNotFoundException;
import com.coforge.ars.model.Airplane;
import com.coforge.ars.repo.AirplaneRepo;
import com.coforge.ars.service.AirplaneService;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    private AirplaneRepo repo;
    private Environment environment;

    @Autowired
    public AirplaneServiceImpl(AirplaneRepo repo,
                               Environment environment) {

        this.repo = repo;
        this.environment = environment;
    }

    @Override
    public Airplane addAirplane(Airplane airplane) {

        return repo.save(airplane);

    }

    @Override
    public Airplane updateAirplane(Long planeId,
                                   Airplane airplane) {

        if (!repo.existsById(planeId)) {
            throw new AirplaneNotFoundException(
                    environment.getProperty("ars.invalid.airplane-notfound"));
        }

        airplane.setPlaneId(planeId);

        return repo.save(airplane);
    }

    @Override
    public void deleteAirplane(Long planeId) {

        if (!repo.existsById(planeId)) {
            throw new AirplaneNotFoundException(
                    environment.getProperty("ars.invalid.airplane-notfound"));
        }

        repo.deleteById(planeId);
    }

    @Override
    public Airplane getAirplaneById(Long planeId) {

        if (!repo.existsById(planeId)) {
            throw new AirplaneNotFoundException(
                    environment.getProperty("ars.invalid.airplane-notfound"));
        }

        Optional<Airplane> airplane = repo.findById(planeId);

        return airplane.get();
    }

    @Override
    public List<Airplane> getAllAirplanes() {

        return repo.findAll();

    }

}