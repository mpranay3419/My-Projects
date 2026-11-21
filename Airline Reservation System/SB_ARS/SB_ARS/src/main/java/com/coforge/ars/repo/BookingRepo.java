package com.coforge.ars.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.ars.model.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {
	List<Booking> findByUserUserId(Long userId);

}
