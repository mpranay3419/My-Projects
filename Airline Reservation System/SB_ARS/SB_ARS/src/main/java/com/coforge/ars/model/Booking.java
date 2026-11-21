package com.coforge.ars.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "tbl_booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@NotNull(message = "User is required")
	private User user;

	@ManyToOne
	@JoinColumn(name = "flight_id")
	@NotNull(message = "Flight is required")
	private Flight flight;

	@NotNull(message = "Number of passengers is required")
	@Positive(message = "Passengers must be greater than 0")
	@Column(nullable = false)
	private Integer passengers;

	@NotBlank(message = "Seat Class is required")
	@Column(nullable = false)
	private String seatClass;

	@Column(nullable = false)
	private Double totalFare;

	@Column(nullable = false)
	private String bookingStatus;

	@Column(nullable = false)
	private LocalDateTime bookingDate;
	
	@Column(nullable = false)
	private String seatNumbers;

	public Booking() {
	}

	public Booking(Long bookingId, @NotNull(message = "User is required") User user,
			@NotNull(message = "Flight is required") Flight flight,
			@NotNull(message = "Number of passengers is required") @Positive(message = "Passengers must be greater than 0") Integer passengers,
			@NotBlank(message = "Seat Class is required") String seatClass, Double totalFare, String bookingStatus,
			LocalDateTime bookingDate, String seatNumbers) {
		super();
		this.bookingId = bookingId;
		this.user = user;
		this.flight = flight;
		this.passengers = passengers;
		this.seatClass = seatClass;
		this.totalFare = totalFare;
		this.bookingStatus = bookingStatus;
		this.bookingDate = bookingDate;
		this.seatNumbers = seatNumbers;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Integer getPassengers() {
		return passengers;
	}

	public void setPassengers(Integer passengers) {
		this.passengers = passengers;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	public Double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(Double totalFare) {
		this.totalFare = totalFare;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(String seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", user=" + user + ", flight=" + flight + ", passengers="
				+ passengers + ", seatClass=" + seatClass + ", totalFare=" + totalFare + ", bookingStatus="
				+ bookingStatus + ", bookingDate=" + bookingDate + ", seatNumbers=" + seatNumbers + "]";
	}

	
}