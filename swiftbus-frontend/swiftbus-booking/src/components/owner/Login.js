import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [name, setName] = useState("");
  const [mobileNo, setMobileNo] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    const formattedName = name.trim();
    const formattedMobileNo = mobileNo.trim();

    // Validate mobile number
    if (!formattedMobileNo || isNaN(formattedMobileNo)) {
      setError("Please enter a valid mobile number.");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:8080/owner/login",
        null,  // No request body, data is sent as query params
        {
          params: {
            name: formattedName,
            mobileNo: formattedMobileNo,
          },
        }
      );

      // Check if the backend returns the owner data
      if (response.data && response.data.id) {
        localStorage.setItem("owner", JSON.stringify(response.data));
        alert("Login successful!");
        navigate("/dashboard");
      } else {
        // If the backend does not return valid owner data
        setError("Invalid credentials, please try again.");
      }
    } catch (err) {
      // Handle error from backend (e.g., 401 Unauthorized)
      if (err.response && err.response.status === 401) {
        setError("Invalid credentials, please try again.");
      } else {
        setError("An error occurred, please try again.");
      }
      console.error("Error during login:", err.response?.data || err.message);
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center">Login</h2>
      <form onSubmit={handleLogin} className="w-50 mx-auto">
        <div className="mb-3">
          <label className="form-label">Name</label>
          <input
            type="text"
            className="form-control"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Mobile No</label>
          <input
            type="text"
            className="form-control"
            value={mobileNo}
            onChange={(e) => setMobileNo(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary w-100">
          Login
        </button>
      </form>
      {error && <p className="text-center text-danger mt-2">{error}</p>}
    </div>
  );
};

export default Login;
