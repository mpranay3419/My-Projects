package com.coforge.ars.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_airport")
public class Airport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long airportId;

	@NotBlank(message = "Airport Name is required")
	@Column(nullable = false)
	private String airportName;

	@NotBlank(message = "Airport Code is required")
	@Column(nullable = false, unique = true)
	private String airportCode;

	@NotBlank(message = "City is required")
	@Column(nullable = false)
	private String city;

    public Airport() {
    }

	public Airport(Long airportId, String airportName, String airportCode, String city) {
		super();
		this.airportId = airportId;
		this.airportName = airportName;
		this.airportCode = airportCode;
		this.city = city;
	}

	public Long getAirportId() {
		return airportId;
	}

	public void setAirportId(Long airportId) {
		this.airportId = airportId;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Airport [airportId=" + airportId + ", airportName=" + airportName + ", airportCode=" + airportCode
				+ ", city=" + city + "]";
	}

    
}