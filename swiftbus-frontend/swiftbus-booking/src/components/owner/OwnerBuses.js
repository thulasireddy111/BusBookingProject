import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

const OwnerBuses = () => {
  const [buses, setBuses] = useState([]);
  const [error, setError] = useState("");
  const { ownerId } = useParams(); // Get ownerId from URL params
  const navigate = useNavigate();

  useEffect(() => {
    fetchBusesForOwner(ownerId);
  }, [ownerId]);

  const fetchBusesForOwner = async (ownerId) => {
    try {
      const response = await axios.get(`http://localhost:8080/owner/getAllOwnerBus/${ownerId}`);
      setBuses(response.data);
    } catch (err) {
      setError("Failed to fetch buses. Please try again.");
      console.error("Fetch Buses Error:", err);
    }
  };

  const handleUpdateBus = (busNo) => {
    navigate(`/update-bus/${busNo}`);
  };

  const handleDeleteBus = async (busNo) => {
    if (window.confirm("Are you sure you want to delete this bus?")) {
      try {
        await axios.delete(`http://localhost:8080/bus/delete/${busNo}`);
        alert("Bus deleted successfully!");
        fetchBusesForOwner(ownerId); // Refresh buses after deletion
      } catch (err) {
        setError("Failed to delete bus.");
        console.error("Delete Bus Error:", err);
      }
    }
  };

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    return date.toLocaleDateString();
  };

  const formatTime = (timeString) => {
    const time = new Date(`1970-01-01T${timeString}`);
    return time.toLocaleTimeString();
  };

  return (
    <div className="container">
      <h2>Buses for the Owner</h2>
      {error && <p className="alert alert-danger">{error}</p>}

      <table className="table table-bordered">
        <thead>
          <tr>
            <th>Bus No</th>
            <th>Bus Name</th>
            <th>Arrival City</th>
            <th>Departure City</th>
            <th>Arrival Date</th>
            <th>Arrival Time</th>
            <th>Departure Date</th>
            <th>Departure Time</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {buses.length > 0 ? (
            buses.map((bus) => (
              <tr key={bus.busNo}>
                <td>{bus.busNo}</td>
                <td>{bus.busName}</td>
                <td>{bus.arrivalCity}</td>
                <td>{bus.departureCity}</td>
                <td>{formatDate(bus.arrivalDate)}</td>
                <td>{formatTime(bus.arrivalTime)}</td>
                <td>{formatDate(bus.departureDate)}</td>
                <td>{formatTime(bus.departureTime)}</td>
                <td>
                  <button className="btn btn-warning" onClick={() => handleUpdateBus(bus.busNo)}>
                    <i className="fas fa-edit"></i> Update
                  </button>
                  <button className="btn btn-danger ms-2" onClick={() => handleDeleteBus(bus.busNo)}>
                    <i className="fas fa-trash"></i> Delete
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="9">No buses found for this owner.</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default OwnerBuses;
