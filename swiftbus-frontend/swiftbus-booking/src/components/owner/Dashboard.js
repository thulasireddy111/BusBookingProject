import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Dashboard = () => {
  const [owners, setOwners] = useState([]);
  const [error, setError] = useState("");
  const [editingOwner, setEditingOwner] = useState(null);
  const [OwnerBuses, setOwnerBuses] = useState([]);
  const [currentOwner, setCurrentOwner] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const loggedOwner = JSON.parse(localStorage.getItem("owner"));
    if (loggedOwner) {
      setCurrentOwner(loggedOwner);
    } else {
      navigate("/login");
    }

    fetchAllOwners();
  }, [navigate]);

  const fetchAllOwners = async () => {
    try {
      const response = await axios.get("http://localhost:8080/owner/get");
      setOwners(response.data);
    } catch (err) {
      setError("Failed to fetch owners. Please try again.");
      console.error("Fetch Owners Error:", err);
    }
  };

  const fetchOwnerBuses = async (ownerId) => {
    try {
      const response = await axios.get(`http://localhost:8080/owner/getAllOwnerBus/${ownerId}`);
      setOwnerBuses(response.data);
    } catch (err) {
      setError("Failed to fetch buses. Please try again.");
      console.error("Fetch Buses Error:", err);
    }
  };

  const handleAddOwner = () => {
    navigate("/signup");
  };

  const handleUpdateOwner = (owner) => {
    setEditingOwner(owner);
  };

  const handleSaveUpdate = async (e) => {
    e.preventDefault();
    try {
      const updatedOwner = {
        ...editingOwner,
        name: editingOwner.name.trim(),
        mobileNo: editingOwner.mobileNo.trim(),
      };
      await axios.put(`http://localhost:8080/owner/update/${editingOwner.id}`, updatedOwner);
      alert("Owner updated successfully!");
      fetchAllOwners();
      setEditingOwner(null);
    } catch (err) {
      setError("Failed to update owner.");
      console.error("Update Owner Error:", err);
    }
  };

  const handleDeleteOwner = async (ownerId) => {
    if (window.confirm("Are you sure you want to delete this owner?")) {
      try {
        await axios.delete(`http://localhost:8080/owner/delete/${ownerId}`);
        alert("Owner deleted successfully!");
        fetchAllOwners();
      } catch (err) {
        setError("Failed to delete owner.");
        console.error("Delete Owner Error:", err);
      }
    }
  };

  const handleViewBuses = (ownerId) => {
    navigate(`/bus-management/${ownerId}`); // Navigate to bus management with ownerId
  };

  const handleAddBus = (ownerId) => {
    navigate(`/add-bus/${ownerId}`);
  };

  const handleUpdateBus = (busNo) => {
    navigate(`/update-bus/${busNo}`);
  };

  const handleDeleteBus = async (busNo) => {
    if (window.confirm("Are you sure you want to delete this bus?")) {
      try {
        await axios.delete(`http://localhost:8080/bus/delete/${busNo}`);
        alert("Bus deleted successfully!");
        fetchOwnerBuses(currentOwner.id);
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
    <div className="container mt-4">
      <h2 className="text-center">Owner Dashboard</h2>
      {error && <p className="alert alert-danger">{error}</p>}

      {/* Show Add Owner button only if logged in as an admin */}
      {currentOwner && currentOwner.role === 'admin' && (
        <div className="d-flex justify-content-between mb-3">
          <button className="btn btn-success" onClick={handleAddOwner}>
            <i className="fas fa-user-plus"></i> Add Owner
          </button>
        </div>
      )}

      {editingOwner && (
        <div className="mt-4">
          <h3>Edit Owner</h3>
          <form onSubmit={handleSaveUpdate}>
            <div className="mb-3">
              <label className="form-label">Name</label>
              <input
                type="text"
                className="form-control"
                value={editingOwner.name}
                onChange={(e) => setEditingOwner({ ...editingOwner, name: e.target.value })}
                required
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Mobile No</label>
              <input
                type="text"
                className="form-control"
                value={editingOwner.mobileNo}
                onChange={(e) => setEditingOwner({ ...editingOwner, mobileNo: e.target.value })}
                required
              />
            </div>
            <button type="submit" className="btn btn-warning">
              <i className="fas fa-save"></i> Save Changes
            </button>
          </form>
        </div>
      )}

      <div className="table-responsive mt-4">
        <table className="table table-bordered">
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Mobile No</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {owners.length > 0 ? (
              owners.map((owner) => (
                <tr key={owner.id}>
                  <td>{owner.id}</td>
                  <td>{owner.name}</td>
                  <td>{owner.mobileNo}</td>
                  <td>
                    {/* Only show actions for the current logged-in owner */}
                    {currentOwner && currentOwner.id === owner.id && (
                      <>
                        <button className="btn btn-warning" onClick={() => handleUpdateOwner(owner)}>
                          <i className="fas fa-edit"></i> Update
                        </button>
                        <button className="btn btn-danger ms-2" onClick={() => handleDeleteOwner(owner.id)}>
                          <i className="fas fa-trash"></i> Delete
                        </button>
                        <button className="btn btn-info ms-2" onClick={() => handleViewBuses(owner.id)}>
                          <i className="fas fa-bus"></i> View Buses
                        </button>
                        <button className="btn btn-success ms-2" onClick={() => handleAddBus(owner.id)}>
                          <i className="fas fa-plus-circle"></i> Add Bus
                        </button>
                      </>
                    )}
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="4">No owners found</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Dashboard;
