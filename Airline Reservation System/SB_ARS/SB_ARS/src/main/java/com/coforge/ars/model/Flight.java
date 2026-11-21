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
@Table(name = "tbl_flight")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long flightId;

	@NotBlank(message = "Flight Number is required")
	@Column(nullable = false, unique = true)
	private String flightNumber;

	@NotNull(message = "Source Airport is required")
	@ManyToOne
	@JoinColumn(name = "source_airport")
	private Airport sourceAirport;

	@NotNull(message = "Destination Airport is required")
	@ManyToOne
	@JoinColumn(name = "destination_airport")
	private Airport destinationAirport;

	@NotNull(message = "Airplane is required")
	@ManyToOne
	@JoinColumn(name = "plane_id")
	private Airplane airplane;

	@NotNull(message = "Departure Time is required")
	@Column(nullable = false)
	private LocalDateTime departureTime;

	@NotNull(message = "Arrival Time is required")
	@Column(nullable = false)
	private LocalDateTime arrivalTime;

	@NotNull(message = "Economy Fare is required")
	@Positive(message = "Economy Fare must be greater than 0")
	@Column(nullable = false)
	private Double economyFare;

	@NotNull(message = "Business Fare is required")
	@Positive(message = "Business Fare must be greater than 0")
	@Column(nullable = false)
	private Double businessFare;

	@NotNull(message = "First Class Fare is required")
	@Positive(message = "First Class Fare must be greater than 0")
	@Column(nullable = false)
	private Double firstClassFare;

	@NotBlank(message = "Flight Status is required")
	@Column(nullable = false)
	private String status;

	@Column(nullable = false)
	private Integer availableSeats;
    public Flight() {
    }

	public Flight(Long flightId, String flightNumber, Airport sourceAirport, Airport destinationAirport,
			Airplane airplane, LocalDateTime departureTime, LocalDateTime arrivalTime, Double economyFare,
			Double businessFare, Double firstClassFare, String status, Integer availableSeats) {
		super();
		this.flightId = flightId;
		this.flightNumber = flightNumber;
		this.sourceAirport = sourceAirport;
		this.destinationAirport = destinationAirport;
		this.airplane = airplane;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.economyFare = economyFare;
		this.businessFare = businessFare;
		this.firstClassFare = firstClassFare;
		this.status = status;
		this.availableSeats = availableSeats;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Airport getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Double getEconomyFare() {
		return economyFare;
	}

	public void setEconomyFare(Double economyFare) {
		this.economyFare = economyFare;
	}

	public Double getBusinessFare() {
		return businessFare;
	}

	public void setBusinessFare(Double businessFare) {
		this.businessFare = businessFare;
	}

	public Double getFirstClassFare() {
		return firstClassFare;
	}

	public void setFirstClassFare(Double firstClassFare) {
		this.firstClassFare = firstClassFare;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", flightNumber=" + flightNumber + ", sourceAirport=" + sourceAirport
				+ ", destinationAirport=" + destinationAirport + ", airplane=" + airplane + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + ", economyFare=" + economyFare + ", businessFare="
				+ businessFare + ", firstClassFare=" + firstClassFare + ", status=" + status + ", availableSeats="
				+ availableSeats + "]";
	}

	

}