import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function BusManagement() {
  const [buses, setBuses] = useState([]);
  const [editBus, setEditBus] = useState(null);
  const [updatedBus, setUpdatedBus] = useState({
    busNo: '',
    busName: '',
    arrivalCity: '',
    departureCity: '',
    arrivalDate: '',
    departureDate: '',
    arrivalTime: '',
    departureTime: '',
  });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();
  const [currentOwner, setCurrentOwner] = useState(null); // Storing the owner object in state

  useEffect(() => {
    // Check if currentOwner is available and load it only once
    const owner = JSON.parse(localStorage.getItem('owner'));
    if (owner) {
      setCurrentOwner(owner); // Set currentOwner only once when it's available
    } else {
      navigate('/login'); // Redirect to login if no owner is found
    }
  }, [navigate]); // Only run once when component mounts

  useEffect(() => {
    // Only fetch buses if currentOwner is set
    if (currentOwner) {
      setLoading(true); // Set loading to true before fetching data
      fetch(`http://localhost:8080/owner/getAllOwnerBus/${currentOwner.id}`)
        .then((response) => response.json())
        .then((data) => {
          const busesForOwner = data[0]?.buses || [];
          setBuses(busesForOwner); // Update buses data
          setLoading(false); // Set loading to false after fetch completes
        })
        .catch((err) => {
          console.error('Fetch error:', err);
          setError('Failed to fetch buses.');
          setLoading(false);
        });
    }
  }, [currentOwner]); // This useEffect only runs when currentOwner changes

  const deleteBus = (busNo) => {
    fetch(`http://localhost:8080/bus/delete/${busNo}`, {
      method: 'DELETE',
    })
      .then(() => {
        setBuses(buses.filter((bus) => bus.busNo !== busNo)); // Update buses after deletion
      })
      .catch((err) => setError('Failed to delete bus.'));
  };

  const handleEditClick = (bus) => {
    setEditBus(bus);
    setUpdatedBus({
      busNo: bus.busNo,
      busName: bus.busName,
      arrivalCity: bus.arrivalCity,
      departureCity: bus.departureCity,
      arrivalDate: bus.arrivalDate,
      departureDate: bus.departureDate,
      arrivalTime: bus.arrivalTime,
      departureTime: bus.departureTime,
    });
  };

  const handleUpdateBus = () => {
    fetch(`http://localhost:8080/bus/update/${updatedBus.busNo}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(updatedBus),
    })
      .then((response) => {
        if (response.ok) {
          setBuses(
            buses.map((bus) => (bus.busNo === updatedBus.busNo ? updatedBus : bus))
          );
          setEditBus(null); // Hide edit form after updating
        } else {
          throw new Error('Failed to update bus.');
        }
      })
      .catch((err) => setError(err.message));
  };

  const handleChange = (e) => {
    setUpdatedBus({ ...updatedBus, [e.target.name]: e.target.value });
  };

  const handleViewDrivers = (busNo) => {
    navigate(`/driver-management/${busNo}`); // Navigate to Driver Management page
  };

  const handleViewSeats = (busNo) => {
    navigate(`/seats-management/${busNo}`); // Navigate to Seats Management page
  };

  if (loading) {
    return <div className="text-center">Loading buses...</div>; // Display loading message while fetching data
  }

  return (
    <div className="container">
      <h2 className="text-center">Bus Management</h2>
      {error && <p className="alert alert-danger">{error}</p>}

      <table className="table table-bordered">
        <thead>
          <tr>
            <th>Bus No</th>
            <th>Bus Name</th>
            <th>Arrival City</th>
            <th>Departure City</th>
            <th>Arrival Date</th>
            <th>Departure Date</th>
            <th>Arrival Time</th>
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
                <td>{bus.arrivalDate}</td>
                <td>{bus.departureDate}</td>
                <td>{bus.arrivalTime}</td>
                <td>{bus.departureTime}</td>
                <td>
                  <button className="btn btn-warning" onClick={() => handleEditClick(bus)}>
                    Edit
                  </button>
                  <button className="btn btn-danger ms-2" onClick={() => deleteBus(bus.busNo)}>
                    Delete
                  </button>
                  <button className="btn btn-info ms-2" onClick={() => handleViewDrivers(bus.busNo)}>
                    View Drivers
                  </button>
                  <button className="btn btn-secondary ms-2" onClick={() => handleViewSeats(bus.busNo)}>
                    View Seats
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="9" className="text-center">
                No buses available for this owner.
              </td>
            </tr>
          )}
        </tbody>
      </table>

      {editBus && (
        <div>
          <h3>Edit Bus</h3>
          <form>
            <div className="form-group">
              <label>Bus No</label>
              <input type="text" className="form-control" name="busNo" value={updatedBus.busNo} readOnly />
            </div>
            <div className="form-group">
              <label>Bus Name</label>
              <input type="text" className="form-control" name="busName" value={updatedBus.busName} onChange={handleChange} />
            </div>
            <div className="form-group">
              <label>Arrival City</label>
              <input type="text" className="form-control" name="arrivalCity" value={updatedBus.arrivalCity} onChange={handleChange} />
            </div>
            <div className="form-group">
              <label>Departure City</label>
              <input type="text" className="form-control" name="departureCity" value={updatedBus.departureCity} onChange={handleChange} />
            </div>
            <div className="form-group">
              <label>Arrival Date</label>
              <input type="date" className="form-control" name="arrivalDate" value={updatedBus.arrivalDate} onChange={handleChange} />
            </div>
            <div className="form-group">
              <label>Departure Date</label>
              <input type="date" className="form-control" name="departureDate" value={updatedBus.departureDate} onChange={handleChange} />
            </div>
            <div className="form-group">
              <label>Arrival Time</label>
              <input type="time" className="form-control" name="arrivalTime" value={updatedBus.arrivalTime} onChange={handleChange} />
            </div>
            <div className="form-group">
              <label>Departure Time</label>
              <input type="time" className="form-control" name="departureTime" value={updatedBus.departureTime} onChange={handleChange} />
            </div>
            <button type="button" className="btn btn-success" onClick={handleUpdateBus}>
              Update Bus
            </button>
          </form>
        </div>
      )}
    </div>
  );
}

export default BusManagement;
