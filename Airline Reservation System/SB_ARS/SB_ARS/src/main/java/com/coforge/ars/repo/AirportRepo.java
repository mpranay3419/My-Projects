package com.coforge.ars.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.ars.model.Airport;

public interface AirportRepo extends JpaRepository<Airport, Long> {

}
