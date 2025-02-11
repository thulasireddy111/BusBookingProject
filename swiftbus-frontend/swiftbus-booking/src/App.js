import React from "react";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import Login from "./components/owner/Login";
import Signup from "./components/owner/Signup";
import Dashboard from "./components/owner/Dashboard";
import AddBus from "./components/owner/AddBus";
import BusManagement from "./components/owner/BusManagement";
import DriverManagement from "./components/owner/DriverManagement"; // Import Driver Management
import SeatsManagement from "./components/owner/SeatsManagement"; // Import Seats Management
import PassengerSignup from "./components/owner/PassenegerSignup"; // New Passenger Signup
import PassengerLogin from "./components/owner/PassengerLogin"; // Updated import for PassengerLogin
import PassengerManagement from "./components/owner/PassengerManagement"; // Passenger Management
import PaymentManagement from "./components/owner/PaymentManagement"; // Import Payment Management
import { ThemeProvider } from 'react-bootstrap'; // Import ThemeProvider from react-bootstrap
import "bootstrap/dist/css/bootstrap.min.css";

const App = () => {
  return (
    <ThemeProvider prefixes={{}}> {/* Wrap your app with ThemeProvider */}
      <Router>
        <Routes>
          {/* Default route redirecting to login page */}
          <Route path="/" element={<Navigate to="/login" />} />

          {/* Owner Routes */}
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/add-bus/:ownerId" element={<AddBus />} />
          <Route path="/bus-management/:ownerId" element={<BusManagement />} />
          <Route path="/driver-management/:busNo" element={<DriverManagement />} />
          <Route path="/seats-management/:busId" element={<SeatsManagement />} />

          {/* Passenger Routes */}
          <Route path="/passenger-signup" element={<PassengerSignup />} /> {/* New Passenger Signup Route */}
          <Route path="/passenger-login" element={<PassengerLogin />} /> {/* New Passenger Login Route */}
          <Route path="/passenger-management" element={<PassengerManagement />} /> {/* New Passenger Management Route */}
          <Route path="/passenger-management/add" element={<PassengerManagement />} /> {/* Add new passenger */}
          <Route path="/passenger-management" element={<PassengerManagement />} /> {/* Update existing passenger */}

          {/* Payment Management Route */}
          <Route path="/payment-management" element={<PaymentManagement />} /> {/* New Payment Management Route */}
        </Routes>
      </Router>
    </ThemeProvider>
  );
};

export default App;
