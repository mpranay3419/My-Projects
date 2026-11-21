package com.coforge.ars.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.ars.model.Airplane;

public interface AirplaneRepo extends JpaRepository<Airplane, Long> {

}
