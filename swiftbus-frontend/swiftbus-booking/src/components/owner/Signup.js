import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Signup = () => {
  const [name, setName] = useState("");
  const [mobileNo, setMobileNo] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSignup = async (e) => {
    e.preventDefault();

    // Trim name and mobileNo to ensure no leading or trailing spaces
    const formattedName = name.trim();
    const formattedMobileNo = mobileNo.trim();

    // Validate mobile number (check if it's valid)
    if (!formattedMobileNo || isNaN(formattedMobileNo)) {
      setError("Please enter a valid mobile number.");
      return;
    }

    try {
      // Sending the signup request to the backend
      const response = await axios.post("http://localhost:8080/owner/add", {
        name: formattedName,
        mobileNo: formattedMobileNo,
      });

      // If signup is successful, alert and redirect to the login page
      alert("Signup successful!");
      navigate("/login"); // Redirect to login page
    } catch (err) {
      // Handle error if signup fails
      setError("Signup failed. Please try again.");
      console.error("Error during signup:", err.response?.data || err.message);
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center">Signup</h2>
      <form onSubmit={handleSignup} className="w-50 mx-auto">
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
          Signup
        </button>
      </form>
      {error && <p className="text-center text-danger mt-2">{error}</p>}
    </div>
  );
};

export default Signup;
