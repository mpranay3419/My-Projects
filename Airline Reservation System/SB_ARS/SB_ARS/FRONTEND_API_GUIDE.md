# SB_ARS Backend Guide for Frontend Development

This document describes the backend structure, API endpoints, data models, and frontend integration notes for the SB_ARS Spring Boot application.

## 1. Project Overview

This project is a flight booking backend built with:

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Hibernate
- Validation
- OpenPDF for ticket generation

### Base URL

- Local backend: http://localhost:2006
- API prefix: /api/v1/ars

### Main Database

- Database: airline_db
- Server port: 2006

---

## 2. Backend Project Structure

```text
src/main/java/com/coforge/ars/
├── controller/        # REST controllers
├── service/           # Business logic interfaces
├── service/impl/      # Service implementations
├── model/             # Entity classes
├── repo/              # JPA repositories
├── dto/               # Data transfer objects
├── exception/         # Custom exception handling
├── util/              # Helper classes like PDF generator
└── SbArsApplication.java
```

### Main responsibilities

- Controller: exposes HTTP endpoints
- Service: contains business logic and validations
- Repo: database access using Spring Data JPA
- Model: database entities
- Exception: handles errors cleanly for frontend consumption

---

## 3. Main Entities and Fields

### User

```json
{
  "userId": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "password": "secret123",
  "role": "CUSTOMER",
  "walletBalance": 5000.0
}
```

### Airport

```json
{
  "airportId": 1,
  "airportName": "Indira Gandhi International Airport",
  "airportCode": "DEL",
  "city": "Delhi"
}
```

### Airplane

```json
{
  "planeId": 1,
  "planeName": "Boeing 737",
  "capacity": 180
}
```

### Flight

```json
{
  "flightId": 1,
  "flightNumber": "AI101",
  "sourceAirport": {
    "airportId": 1,
    "airportName": "Indira Gandhi International Airport",
    "airportCode": "DEL",
    "city": "Delhi"
  },
  "destinationAirport": {
    "airportId": 2,
    "airportName": "Chhatrapati Shivaji Maharaj Airport",
    "airportCode": "BOM",
    "city": "Mumbai"
  },
  "airplane": {
    "planeId": 1,
    "planeName": "Boeing 737",
    "capacity": 180
  },
  "departureTime": "2026-08-01T10:30:00",
  "arrivalTime": "2026-08-01T13:00:00",
  "economyFare": 4500.0,
  "businessFare": 7500.0,
  "firstClassFare": 12000.0,
  "status": "ON TIME",
  "availableSeats": 180
}
```

### Booking

```json
{
  "bookingId": 1,
  "user": {
    "userId": 1,
    "name": "John Doe"
  },
  "flight": {
    "flightId": 1,
    "flightNumber": "AI101"
  },
  "passengers": 2,
  "seatClass": "ECONOMY",
  "totalFare": 9000.0,
  "bookingStatus": "CONFIRMED",
  "bookingDate": "2026-08-01T09:00:00",
  "seatNumbers": "A1, A2"
}
```

---

## 4. API Endpoints

## 4.1 User APIs

| Method | Endpoint | Purpose |
|---|---|---|
| POST | /api/v1/ars/users | Create user |
| GET | /api/v1/ars/users | Get all users |
| GET | /api/v1/ars/users/{userId} | Get user by ID |
| PUT | /api/v1/ars/users/{userId} | Update user |
| DELETE | /api/v1/ars/users/{userId} | Delete user |
| POST | /api/v1/ars/users/register | Register user |
| POST | /api/v1/ars/users/login | Login user |
| GET | /api/v1/ars/users/{userId}/wallet | Get wallet balance |
| PUT | /api/v1/ars/users/{userId}/wallet?amount=... | Add money to wallet |

### Create User

Request body:

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "secret123",
  "role": "CUSTOMER",
  "walletBalance": 5000.0
}
```

### Login

Request body:

```json
{
  "email": "john@example.com",
  "password": "secret123"
}
```

### Add Money to Wallet

Endpoint:

```http
PUT /api/v1/ars/users/1/wallet?amount=1000
```

---

## 4.2 Airport APIs

| Method | Endpoint | Purpose |
|---|---|---|
| POST | /api/v1/ars/airports | Add airport |
| GET | /api/v1/ars/airports | Get all airports |
| GET | /api/v1/ars/airports/{airportId} | Get airport by ID |
| PUT | /api/v1/ars/airports/{airportId} | Update airport |
| DELETE | /api/v1/ars/airports/{airportId} | Delete airport |

### Add Airport

```json
{
  "airportName": "Chhatrapati Shivaji Maharaj Airport",
  "airportCode": "BOM",
  "city": "Mumbai"
}
```

---

## 4.3 Airplane APIs

| Method | Endpoint | Purpose |
|---|---|---|
| POST | /api/v1/ars/airplanes | Add airplane |
| GET | /api/v1/ars/airplanes | Get all airplanes |
| GET | /api/v1/ars/airplanes/{planeId} | Get airplane by ID |
| PUT | /api/v1/ars/airplanes/{planeId} | Update airplane |
| DELETE | /api/v1/ars/airplanes/{planeId} | Delete airplane |

### Add Airplane

```json
{
  "planeName": "Airbus A320",
  "capacity": 220
}
```

---

## 4.4 Flight APIs

| Method | Endpoint | Purpose |
|---|---|---|
| POST | /api/v1/ars/flights | Add flight |
| GET | /api/v1/ars/flights | Get all flights |
| GET | /api/v1/ars/flights/{flightId} | Get flight by ID |
| PUT | /api/v1/ars/flights/{flightId} | Update flight |
| DELETE | /api/v1/ars/flights/{flightId} | Delete flight |
| GET | /api/v1/ars/flights/search?sourceAirportId=1&destinationAirportId=2 | Search flights |
| PUT | /api/v1/ars/flights/{flightId}/status?status=ON%20TIME | Update flight status |

### Search Flights

Example:

```http
GET /api/v1/ars/flights/search?sourceAirportId=1&destinationAirportId=2
```

---

## 4.5 Booking APIs

| Method | Endpoint | Purpose |
|---|---|---|
| POST | /api/v1/ars/bookings/book | Book a flight |
| GET | /api/v1/ars/bookings | Get all bookings |
| GET | /api/v1/ars/bookings/{bookingId} | Get booking by ID |
| PUT | /api/v1/ars/bookings/{bookingId} | Update booking |
| DELETE | /api/v1/ars/bookings/{bookingId} | Delete booking |
| GET | /api/v1/ars/bookings/user/{userId} | Get bookings for a user |
| GET | /api/v1/ars/bookings/{bookingId}/ticket | Download ticket PDF |

### Book Flight

Request body:

```json
{
  "user": {
    "userId": 1
  },
  "flight": {
    "flightId": 1
  },
  "passengers": 2,
  "seatClass": "ECONOMY"
}
```

### Notes for booking

- Valid seat classes: ECONOMY, BUSINESS, FIRST
- Booking amount is calculated based on fare × passengers
- If wallet balance is insufficient, booking fails
- If seats are unavailable, booking status becomes WAITING
- If seats are available, booking status becomes CONFIRMED

---

## 5. Important Business Rules

These rules are important for frontend validation:

- User email must be unique
- Password minimum length is 6
- Wallet balance cannot be negative
- Flight fares must be positive
- Booking passengers must be greater than 0
- Seat class must be one of: ECONOMY, BUSINESS, FIRST
- Search flights requires both source and destination airport IDs
- Booking uses wallet balance for payment

---

## 6. Error Handling

The backend returns error responses for invalid requests or missing resources.

### Common error behavior

- 400 Bad Request for invalid input or business rule failure
- 404-style style messages may be returned as plain text/error messages depending on the exception

### Frontend should handle:

- failed login
- insufficient wallet balance
- invalid seat class
- missing flight or user
- validation errors

---

## 7. Frontend Integration Notes

### Recommended flow

1. Register or login user
2. Load airports and airplanes
3. Search flights using source and destination airport IDs
4. Select a flight and book it
5. View booking history for the current user
6. Download ticket PDF

### CORS note

If the frontend runs on a different port, make sure CORS is allowed. The current backend does not appear to include explicit CORS configuration in the provided files.

### Example fetch call for login

```javascript
const response = await fetch('http://localhost:2006/api/v1/ars/users/login', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({
    email: 'john@example.com',
    password: 'secret123'
  })
});

const data = await response.json();
console.log(data);
```

### Example fetch call to search flights

```javascript
const response = await fetch('http://localhost:2006/api/v1/ars/flights/search?sourceAirportId=1&destinationAirportId=2');
const flights = await response.json();
console.log(flights);
```

### Example fetch call to book a flight

```javascript
const response = await fetch('http://localhost:2006/api/v1/ars/bookings/book', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({
    user: { userId: 1 },
    flight: { flightId: 1 },
    passengers: 2,
    seatClass: 'ECONOMY'
  })
});

const booking = await response.json();
console.log(booking);
```

### Example fetch to download ticket PDF

```javascript
window.open('http://localhost:2006/api/v1/ars/bookings/1/ticket');
```

---

## 8. Suggested Frontend Pages

A frontend can be built around these pages:

- Home page
- Login / Register page
- Airport list page
- Flight search page
- Flight details page
- Booking page
- User dashboard / wallet page
- Booking history page
- Ticket download page

---

## 9. Suggested Data Flow

```text
User -> Login/Register -> Search Flights -> Book Flight -> Wallet Deduction -> Booking Confirmation -> Download Ticket
```

---

## 10. Quick Start for Frontend Team

- Use the backend at http://localhost:2006
- Use JSON payloads for POST/PUT requests
- Store user ID and user details after login
- Use the wallet balance to validate booking before payment
- Use the booking history endpoint to display past bookings

---

If you want, the next step can be to create a React frontend starter that connects directly to this backend.
