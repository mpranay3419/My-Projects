package com.coforge.ars.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coforge.ars.model.Booking;
import com.coforge.ars.service.BookingService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/ars/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Book Flight
    @PostMapping("/book")
    public ResponseEntity<Booking> bookFlight(
            @RequestBody Booking booking) {

        Booking bookedFlight = bookingService.bookFlight(booking);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookedFlight);
    }

    // Get All Bookings (Admin)
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {

        return ResponseEntity.ok(
                bookingService.getAllBookings());
    }

    // Get Booking By Id
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(
            @PathVariable Long bookingId) {

        Booking booking = bookingService.getBookingById(bookingId);

        return ResponseEntity.ok(booking);
    }

    // Update Booking
    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable Long bookingId,
            @RequestBody Booking booking) {

        Booking updatedBooking =
                bookingService.updateBooking(bookingId, booking);

        return ResponseEntity.ok(updatedBooking);
    }

    // Delete Booking
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> deleteBooking(
            @PathVariable Long bookingId) {

        bookingService.deleteBooking(bookingId);

        return ResponseEntity.ok("Booking Deleted Successfully");
    }

    // Customer Booking History
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(
            @PathVariable Long userId) {

        List<Booking> bookings =
                bookingService.getBookingsByUser(userId);

        return ResponseEntity.ok(bookings);
    }

    // Download Ticket PDF
    @GetMapping("/{bookingId}/ticket")
    public ResponseEntity<InputStreamResource> downloadTicket(
            @PathVariable Long bookingId) {

        ByteArrayInputStream pdf =
                bookingService.generateTicket(bookingId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=ticket.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
    
    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<String> cancelBooking(
            @PathVariable Long bookingId){

        bookingService.cancelBooking(bookingId);

        return ResponseEntity.ok("Booking Cancelled Successfully");

    }
}