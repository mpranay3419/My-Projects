package com.coforge.ars.service.impl;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.coforge.ars.exception.BookingNotFoundException;
import com.coforge.ars.exception.FlightNotFoundException;
import com.coforge.ars.exception.UserNotFoundException;
import com.coforge.ars.model.Booking;
import com.coforge.ars.model.Flight;
import com.coforge.ars.model.User;
import com.coforge.ars.repo.BookingRepo;
import com.coforge.ars.repo.FlightRepo;
import com.coforge.ars.repo.UserRepo;
import com.coforge.ars.service.BookingService;
import com.coforge.ars.util.PdfGenerator;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepo bookingRepo;
    private UserRepo userRepo;
    private FlightRepo flightRepo;
    private Environment environment;

    @Autowired
    public BookingServiceImpl(BookingRepo bookingRepo,
                              UserRepo userRepo,
                              FlightRepo flightRepo,
                              Environment environment) {

        this.bookingRepo = bookingRepo;
        this.userRepo = userRepo;
        this.flightRepo = flightRepo;
        this.environment = environment;
    }

    @Override
    public Booking bookFlight(Booking booking) {

        // User Validation
        if (!userRepo.existsById(booking.getUser().getUserId())) {
            throw new UserNotFoundException(
                    environment.getProperty("ars.invalid.user-notfound"));
        }

        // Flight Validation
        if (!flightRepo.existsById(booking.getFlight().getFlightId())) {
            throw new FlightNotFoundException(
                    environment.getProperty("ars.invalid.flight-notfound"));
        }

        User user = userRepo.findById(
                booking.getUser().getUserId()).get();

        Flight flight = flightRepo.findById(
                booking.getFlight().getFlightId()).get();

        double fare = 0;

        if (booking.getSeatClass().equalsIgnoreCase("ECONOMY")) {
            fare = flight.getEconomyFare();
        }
        else if (booking.getSeatClass().equalsIgnoreCase("BUSINESS")) {
            fare = flight.getBusinessFare();
        }
        else if (booking.getSeatClass().equalsIgnoreCase("FIRST")) {
            fare = flight.getFirstClassFare();
        }
        else {
            throw new RuntimeException(
                    environment.getProperty("ars.invalid.seatclass"));
        }

        double totalFare = fare * booking.getPassengers();

        // Wallet Check
        if (user.getWalletBalance() < totalFare) {
            throw new RuntimeException(
                    environment.getProperty("ars.invalid.balance"));
        }

        // Seat Availability
     // Seat Availability
        if (flight.getAvailableSeats() >= booking.getPassengers()) {

            // Already booked seats
            int bookedSeats = flight.getAirplane().getCapacity()
                    - flight.getAvailableSeats();

            // Deduct Seats
            flight.setAvailableSeats(
                    flight.getAvailableSeats()
                    - booking.getPassengers());

            // Generate Seat Numbers
            StringBuilder seats = new StringBuilder();

            for (int i = 1; i <= booking.getPassengers(); i++) {

                seats.append("A").append(bookedSeats + i);

                if (i != booking.getPassengers()) {
                    seats.append(", ");
                }
            }

            booking.setSeatNumbers(seats.toString());

            // Deduct Wallet
            user.setWalletBalance(
                    user.getWalletBalance()
                    - totalFare);

            userRepo.save(user);
            flightRepo.save(flight);

            booking.setBookingStatus("CONFIRMED");

        } else {

            booking.setBookingStatus("WAITING");
            booking.setSeatNumbers("Not Assigned");

        }

        booking.setUser(user);
        booking.setFlight(flight);
        booking.setTotalFare(totalFare);
        booking.setBookingDate(LocalDateTime.now());

        return bookingRepo.save(booking);
    }

    @Override
    public Booking updateBooking(Long bookingId,
                                 Booking booking) {

        if (!bookingRepo.existsById(bookingId)) {
            throw new BookingNotFoundException(
                    environment.getProperty("ars.invalid.booking-notfound"));
        }

        booking.setBookingId(bookingId);

        return bookingRepo.save(booking);
    }

    @Override
    public void deleteBooking(Long bookingId) {

        if (!bookingRepo.existsById(bookingId)) {
            throw new BookingNotFoundException(
                    environment.getProperty("ars.invalid.booking-notfound"));
        }

        bookingRepo.deleteById(bookingId);
    }

    @Override
    public Booking getBookingById(Long bookingId) {

        if (!bookingRepo.existsById(bookingId)) {
            throw new BookingNotFoundException(
                    environment.getProperty("ars.invalid.booking-notfound"));
        }

        Optional<Booking> booking =
                bookingRepo.findById(bookingId);

        return booking.get();
    }

    @Override
    public List<Booking> getAllBookings() {

        return bookingRepo.findAll();

    }

    @Override
    public List<Booking> getBookingsByUser(Long userId) {

        if (!userRepo.existsById(userId)) {
            throw new UserNotFoundException(
                    environment.getProperty("ars.invalid.user-notfound"));
        }

        return bookingRepo.findByUserUserId(userId);
    }
    
    @Override
    public ByteArrayInputStream generateTicket(Long bookingId) {

        System.out.println("Booking ID Received: " + bookingId);

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() ->
                new BookingNotFoundException(
                environment.getProperty("ars.invalid.booking-notfound")));

        System.out.println("Booking Found: " + booking.getBookingId());

        return PdfGenerator.generateTicket(booking);
    }
    
    @Override
    public void cancelBooking(Long bookingId) {

        if (!bookingRepo.existsById(bookingId)) {

            throw new BookingNotFoundException(
                    environment.getProperty("ars.invalid.booking-notfound"));
        }

        Booking booking = bookingRepo.findById(bookingId).get();

        if (booking.getBookingStatus().equalsIgnoreCase("CANCELLED")) {

            throw new RuntimeException("Booking Already Cancelled");
        }

        User user = booking.getUser();

        Flight flight = booking.getFlight();

        // Refund Wallet
        user.setWalletBalance(
                user.getWalletBalance()
                + booking.getTotalFare());

        // Increase Available Seats
        flight.setAvailableSeats(
                flight.getAvailableSeats()
                + booking.getPassengers());

        // Update Booking
        booking.setBookingStatus("CANCELLED");

        booking.setSeatNumbers("Cancelled");

        userRepo.save(user);

        flightRepo.save(flight);

        bookingRepo.save(booking);

    }
}