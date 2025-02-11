import React, { useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

const AddBus = () => {
  const { ownerId } = useParams(); // Get ownerId from URL
  const [busName, setBusName] = useState("");
  const [arrivalCity, setArrivalCity] = useState("");
  const [departureCity, setDepartureCity] = useState("");
  const [arrivalDate, setArrivalDate] = useState("");
  const [departureDate, setDepartureDate] = useState("");
  const [arrivalTime, setArrivalTime] = useState("");
  const [departureTime, setDepartureTime] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError("");

    const formattedArrivalDate = new Date(arrivalDate).toISOString().split("T")[0];
    const formattedDepartureDate = new Date(departureDate).toISOString().split("T")[0];

    const formattedArrivalTime = arrivalTime + ":00";
    const formattedDepartureTime = departureTime + ":00";

    const busData = {
      busName,
      arrivalCity,
      departureCity,
      arrivalDate: formattedArrivalDate,
      departureDate: formattedDepartureDate,
      arrivalTime: formattedArrivalTime,
      departureTime: formattedDepartureTime,
      ownerId: { id: ownerId }, // Automatically use the owner ID from the URL
    };

    try {
      const response = await axios.post("http://localhost:8080/bus/add", busData, {
        headers: { "Content-Type": "application/json" },
      });

      console.log("Bus added successfully:", response.data);
      alert("✅ Bus added successfully!");

      // Clear form fields
      setBusName("");
      setArrivalCity("");
      setDepartureCity("");
      setArrivalDate("");
      setDepartureDate("");
      setArrivalTime("");
      setDepartureTime("");
    } catch (error) {
      console.error("Error adding bus: ", error.response?.data || error.message);
      setError("❌ Error adding bus: " + (error.response?.data.message || error.message));
    }
  };

  return (
    <div className="container mt-4">
      <div className="card shadow-lg p-4">
        <h2 className="text-center text-primary mb-4">Add a New Bus</h2>

        {error && <div className="alert alert-danger text-center">{error}</div>}

        <form onSubmit={handleSubmit}>
          <div className="row">
            {/* Left Column */}
            <div className="col-md-6">
              <div className="form-group mb-3">
                <label className="fw-bold">Bus Name:</label>
                <input type="text" className="form-control" value={busName} onChange={(e) => setBusName(e.target.value)} required />
              </div>

              <div className="form-group mb-3">
                <label className="fw-bold">Arrival City:</label>
                <input type="text" className="form-control" value={arrivalCity} onChange={(e) => setArrivalCity(e.target.value)} required />
              </div>

              <div className="form-group mb-3">
                <label className="fw-bold">Departure City:</label>
                <input type="text" className="form-control" value={departureCity} onChange={(e) => setDepartureCity(e.target.value)} required />
              </div>

              <div className="form-group mb-3">
                <label className="fw-bold">Arrival Date:</label>
                <input type="date" className="form-control" value={arrivalDate} onChange={(e) => setArrivalDate(e.target.value)} required />
              </div>
            </div>

            {/* Right Column */}
            <div className="col-md-6">
              <div className="form-group mb-3">
                <label className="fw-bold">Departure Date:</label>
                <input type="date" className="form-control" value={departureDate} onChange={(e) => setDepartureDate(e.target.value)} required />
              </div>

              <div className="form-group mb-3">
                <label className="fw-bold">Arrival Time:</label>
                <input type="time" className="form-control" value={arrivalTime} onChange={(e) => setArrivalTime(e.target.value)} required />
              </div>

              <div className="form-group mb-3">
                <label className="fw-bold">Departure Time:</label>
                <input type="time" className="form-control" value={departureTime} onChange={(e) => setDepartureTime(e.target.value)} required />
              </div>
            </div>
          </div>

          <div className="text-center">
            <button type="submit" className="btn btn-primary px-4 py-2">➕ Add Bus</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default AddBus;
