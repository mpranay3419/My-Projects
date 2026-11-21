package com.coforge.ars.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.ars.model.Flight;

public interface FlightRepo extends JpaRepository<Flight, Long> {
	
	List<Flight> findBySourceAirportAirportIdAndDestinationAirportAirportId(
	        Long sourceAirportId,
	        Long destinationAirportId
	);

}
