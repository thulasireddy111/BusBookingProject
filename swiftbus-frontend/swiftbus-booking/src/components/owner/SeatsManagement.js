import React, { useState, useEffect } from 'react';
import axios from 'axios';

const SeatsManagement = () => {
  const [seatNo, setSeatNo] = useState('');
  const [availableSeats, setAvailableSeats] = useState([]);
  const [message, setMessage] = useState('');
  
  // Hardcode busNo here (manually provide busNo)
  const busNo = 2; // Example busNo, replace with the actual bus number you want to add seats to

  // Function to fetch available seats for the bus
  const fetchAvailableSeats = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/seats/available?busId=${busNo}`);
      setAvailableSeats(response.data);
    } catch (error) {
      console.error('Error fetching available seats:', error);
      setMessage('Error fetching available seats: ' + (error.response?.data || error.message));
    }
  };

  // Function to handle adding a seat
  const handleAddSeat = async () => {
    if (!seatNo) {
      setMessage('Please provide a seat number.');
      return;
    }

    try {
      // Seat data with busNo manually set
      const seatData = {
        seatNo: parseInt(seatNo),
        busId: { busNo: busNo }, // Manually set busNo
      };

      const response = await axios.post('http://localhost:8080/seats/addSeat', seatData);
      setMessage('Seat added successfully!'); // Success message
      setSeatNo(''); // Clear input field after adding
      fetchAvailableSeats(); // Refresh the available seats list
    } catch (error) {
      console.error('Error adding seat:', error);
      // Check if the error has a response and extract the message
      const errorMessage = error.response?.data || error.message || 'Unknown error';
      setMessage('Error adding seat: ' + errorMessage);
    }
  };

  // Fetch available seats when the component mounts
  useEffect(() => {
    fetchAvailableSeats();
  }, [busNo]); // Re-run when busNo changes

  return (
    <div className="container mt-4">
      <h2 className="text-center mb-4">Manage Seats for Bus {busNo}</h2>

      {/* Available Seats List */}
      <div className="mb-4">
        <h3>Available Seats</h3>
        <ul className="list-group">
          {availableSeats.length > 0 ? (
            availableSeats.map((seat) => (
              <li key={seat.bookingId} className="list-group-item">
                Seat No: {seat.seatNo} - 
                <span className={seat.available ? 'text-success' : 'text-danger'}>
                  {seat.available ? 'Available' : 'Booked'}
                </span>
              </li>
            ))
          ) : (
            <p className="text-muted">No available seats found for this bus.</p>
          )}
        </ul>
      </div>

      {/* Add Seat Form */}
      <div className="mb-4">
        <h3>Add a Seat</h3>
        <div className="input-group">
          <input
            type="number"
            className="form-control"
            placeholder="Seat Number"
            value={seatNo}
            onChange={(e) => setSeatNo(e.target.value)}
          />
          <button className="btn btn-primary" onClick={handleAddSeat}>Add Seat</button>
        </div>
      </div>

      {/* Message */}
      {message && <div className="alert alert-info">{message}</div>}
    </div>
  );
};

export default SeatsManagement;
