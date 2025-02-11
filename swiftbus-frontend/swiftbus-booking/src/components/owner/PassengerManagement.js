import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const PassengerManagement = () => {
  const [passengers, setPassengers] = useState([]);
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [editingPassenger, setEditingPassenger] = useState(null);
  const [updatedPassenger, setUpdatedPassenger] = useState({
    passengerName: "",
    email: "",
    mobileNo: "",
  });
  const navigate = useNavigate();

  // Fetch passengers from the backend
  useEffect(() => {
    const fetchPassengers = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/passenger/getAllPassengers"
        );
        setPassengers(response.data);
      } catch (err) {
        setError("Failed to fetch passengers");
      }
    };

    fetchPassengers();
  }, []);

  // Handle edit button click and populate the form with passenger details
  const handleEditPassenger = (passenger) => {
    setEditingPassenger(passenger);
    setUpdatedPassenger({
      passengerName: passenger.passengerName,
      email: passenger.email,
      mobileNo: passenger.mobileNo,
    });
  };

  // Handle form change for the update
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUpdatedPassenger((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // Handle form submission to update passenger details
  const handleUpdatePassenger = async (e) => {
    e.preventDefault();
    try {
      await axios.put(
        `http://localhost:8080/passenger/updatePassenger/${editingPassenger.passengerId}`,
        updatedPassenger
      );
      setSuccessMessage("Passenger details updated successfully");
      // Re-fetch passengers after successful update
      const response = await axios.get("http://localhost:8080/passenger/getAllPassengers");
      setPassengers(response.data);
      setEditingPassenger(null); // Close the form
    } catch (err) {
      setError("Failed to update passenger details");
    }
  };

  // Handle canceling the edit form
  const handleCancelEdit = () => {
    setEditingPassenger(null); // Close the form without updating
  };

  // Handle booking a seat
  const handleBookSeat = (passengerId, passenger) => {
    const seatId = "predefinedSeatId"; // Replace with actual logic for selecting seat ID
    navigate("/payment-management", {
      state: {
        passengerId,
        seatId,
        passenger,
      },
    });
  };

  return (
    <div className="container mt-4">
      <h2>Passenger Management</h2>
      <button onClick={() => navigate("/passenger-management/add")} className="btn btn-primary mb-3">
        Add New Passenger
      </button>

      {successMessage && <div className="alert alert-success">{successMessage}</div>}
      {error && <div className="alert alert-danger">{error}</div>}

      {editingPassenger ? (
        <div className="card mb-4">
          <div className="card-body">
            <h4>Edit Passenger Details</h4>
            <form onSubmit={handleUpdatePassenger}>
              <div className="form-group">
                <label>Passenger Name</label>
                <input
                  type="text"
                  className="form-control"
                  name="passengerName"
                  value={updatedPassenger.passengerName}
                  onChange={handleInputChange}
                  required
                />
              </div>
              <div className="form-group">
                <label>Email</label>
                <input
                  type="email"
                  className="form-control"
                  name="email"
                  value={updatedPassenger.email}
                  onChange={handleInputChange}
                  required
                />
              </div>
              <div className="form-group">
                <label>Mobile Number</label>
                <input
                  type="text"
                  className="form-control"
                  name="mobileNo"
                  value={updatedPassenger.mobileNo}
                  onChange={handleInputChange}
                  required
                />
              </div>
              <button type="submit" className="btn btn-success mr-2">
                Update
              </button>
              <button type="button" onClick={handleCancelEdit} className="btn btn-secondary">
                Cancel
              </button>
            </form>
          </div>
        </div>
      ) : (
        <table className="table table-bordered">
          <thead className="thead-dark">
            <tr>
              <th>Passenger ID</th>
              <th>Passenger Name</th>
              <th>Email</th>
              <th>Mobile Number</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {passengers.length > 0 ? (
              passengers.map((passenger) => (
                <tr key={passenger.passengerId}>
                  <td>{passenger.passengerId}</td>
                  <td>{passenger.passengerName}</td>
                  <td>{passenger.email}</td>
                  <td>{passenger.mobileNo}</td>
                  <td>
                    <button
                      onClick={() => handleEditPassenger(passenger)}
                      className="btn btn-warning btn-sm mr-2"
                    >
                      Edit
                    </button>
                    <button
                      onClick={() => handleBookSeat(passenger.passengerId, passenger)}
                      className="btn btn-success btn-sm"
                    >
                      Book Seat
                    </button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="5" className="text-center">No passengers found</td>
              </tr>
            )}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default PassengerManagement;
