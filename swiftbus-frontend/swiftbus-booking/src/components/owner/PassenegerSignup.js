import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom"; // For redirection after successful signup

const PassengerSignup = () => {
  const [passengerName, setPassengerName] = useState("");
  const [email, setEmail] = useState("");
  const [mobileNo, setMobileNo] = useState("");
  const [busNo, setBusNo] = useState(2); // Bus No can be dynamic or hardcoded
  const [message, setMessage] = useState("");
  const navigate = useNavigate(); // To redirect after successful signup

  const handleSubmit = async (e) => {
    e.preventDefault();

    const passengerData = {
      passengerName,
      email,
      mobileNo: Number(mobileNo),
      passBus: {
        busNo: busNo,
      },
    };

    try {
      const response = await axios.post("http://localhost:8080/passenger/addPassenger", passengerData);
      setMessage("Passenger added successfully!");
      setTimeout(() => {
        // Redirect to the passenger dashboard or login page after successful signup
        navigate("/passenger-dashboard");
      }, 2000);
    } catch (error) {
      setMessage("Error adding passenger: " + (error.response?.data || error.message));
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center mb-4">Passenger Signup</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="passengerName" className="form-label">Passenger Name</label>
          <input
            type="text"
            className="form-control"
            id="passengerName"
            value={passengerName}
            onChange={(e) => setPassengerName(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">Email</label>
          <input
            type="email"
            className="form-control"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="mobileNo" className="form-label">Mobile Number</label>
          <input
            type="text"
            className="form-control"
            id="mobileNo"
            value={mobileNo}
            onChange={(e) => setMobileNo(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="busNo" className="form-label">Bus No</label>
          <input
            type="number"
            className="form-control"
            id="busNo"
            value={busNo}
            onChange={(e) => setBusNo(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">Signup</button>
      </form>

      {message && <div className="alert alert-info mt-4">{message}</div>}
    </div>
  );
};

export default PassengerSignup;
