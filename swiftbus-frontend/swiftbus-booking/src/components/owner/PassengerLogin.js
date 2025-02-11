import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom"; // For redirection after successful login

const PassengerLogin = () => {
  const [email, setEmail] = useState("");
  const [mobileNo, setMobileNo] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  // Handle login form submission
  const handleLogin = async (e) => {
    e.preventDefault();
    const passengerData = { email, mobileNo };

    try {
      // Sending a POST request to the backend to validate login
      const response = await axios.post(
        "http://localhost:8080/passenger/login",
        passengerData
      );

      if (response.status === 200) {
        // If login is successful, navigate to the Passenger Management page
        navigate("/passenger-management"); // Redirect to Passenger Management
      }
    } catch (err) {
      // Handle error if login fails
      if (err.response && err.response.status === 401) {
        setError("Invalid email or mobile number");
      } else {
        setError("Something went wrong. Please try again.");
      }
    }
  };

  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h2 className="text-center">Passenger Login</h2>
              <form onSubmit={handleLogin}>
                <div className="mb-3">
                  <label htmlFor="email" className="form-label">
                    Email
                  </label>
                  <input
                    type="email"
                    id="email"
                    className="form-control"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="mobileNo" className="form-label">
                    Mobile Number
                  </label>
                  <input
                    type="text"
                    id="mobileNo"
                    className="form-control"
                    value={mobileNo}
                    onChange={(e) => setMobileNo(e.target.value)}
                    required
                  />
                </div>
                {error && <div className="alert alert-danger">{error}</div>}
                <button type="submit" className="btn btn-primary w-100">
                  Login
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PassengerLogin;
