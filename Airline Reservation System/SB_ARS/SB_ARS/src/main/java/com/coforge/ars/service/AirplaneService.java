package com.coforge.ars.service;

import java.util.List;

import com.coforge.ars.model.Airplane;

public interface AirplaneService {

    Airplane addAirplane(Airplane airplane);

    Airplane updateAirplane(Long planeId, Airplane airplane);

    void deleteAirplane(Long planeId);

    Airplane getAirplaneById(Long planeId);

    List<Airplane> getAllAirplanes();

}