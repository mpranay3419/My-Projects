import { BrowserRouter, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";

import Login from "./pages/Login";
import Register from "./pages/Register";
import SearchFlights from "./pages/SearchFlights";
import Booking from "./pages/Booking";
import MyBookings from "./pages/MyBookings";
import Admin from "./pages/Admin";
import ManageAirports from "./pages/ManageAirports";
import ManageFlights from "./pages/ManageFlights";
import ManageAirplanes from "./pages/ManageAirplanes";
import ViewAllBookings from "./pages/ViewAllBookings";

function App() {

  return (

    <BrowserRouter>

      <Navbar />

      <Routes>

        <Route path="/" element={<Login />} />

        <Route path="/register" element={<Register />} />

        <Route path="/search" element={<SearchFlights />} />

        <Route path="/booking/:id" element={<Booking />} />

        <Route path="/bookings" element={<MyBookings />} />

        <Route path="/admin" element={<Admin />} />

        <Route
          path="/admin/airports"
          element={<ManageAirports />}
        />

        <Route
          path="/admin/flights"
          element={<ManageFlights />}
        />

        <Route
          path="/admin/airplanes"
          element={<ManageAirplanes />}
        />

        <Route
          path="/admin/bookings"
          element={<ViewAllBookings />}
        />

      </Routes>

    </BrowserRouter>

  );

}

export default App;