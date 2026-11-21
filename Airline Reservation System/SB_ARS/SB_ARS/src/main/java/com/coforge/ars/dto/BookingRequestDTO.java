package com.coforge.ars.dto;

public class BookingRequestDTO {

    private Long userId;
    private Long flightId;
    private Integer passengers;
    private String seatClass;

    public BookingRequestDTO() {
    }

    public BookingRequestDTO(Long userId,
                             Long flightId,
                             Integer passengers,
                             String seatClass) {

        this.userId = userId;
        this.flightId = flightId;
        this.passengers = passengers;
        this.seatClass = seatClass;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
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

}