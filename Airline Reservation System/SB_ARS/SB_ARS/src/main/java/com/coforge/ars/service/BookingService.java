package com.coforge.ars.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.coforge.ars.model.Booking;

public interface BookingService {

	Booking bookFlight(Booking booking);

    Booking updateBooking(Long bookingId, Booking booking);

    void deleteBooking(Long bookingId);

    Booking getBookingById(Long bookingId);

    List<Booking> getAllBookings();
    
    List<Booking> getBookingsByUser(Long userId);
    
    ByteArrayInputStream generateTicket(Long bookingId);
    
    void cancelBooking(Long bookingId);

}