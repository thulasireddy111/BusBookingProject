import React, { useEffect, useState } from "react";
import axios from "axios";
import { Button, Table, Form } from "react-bootstrap";
import { FaTrash, FaEdit, FaPlus } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

const DriverManagement = () => {
  const [drivers, setDrivers] = useState([]);
  const [buses, setBuses] = useState([]);
  const [selectedBus, setSelectedBus] = useState(""); // Store selected bus
  const [driverName, setDriverName] = useState("");
  const [driverNo, setDriverNo] = useState("");
  const [driverNoToDelete, setDriverNoToDelete] = useState(""); // Store driverNo for delete operation
  const [error, setError] = useState("");
  const [isEditing, setIsEditing] = useState(false);
  const [editingDriverId, setEditingDriverId] = useState(null);
  const navigate = useNavigate();
  const busNo = window.location.pathname.split("/")[2]; // Get busNo from URL

  // Fetch drivers and buses when the component mounts
  useEffect(() => {
    fetchDrivers();
    fetchBuses();
  }, [busNo]);

  const fetchDrivers = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/driver/getAllDriversByBusNo/${busNo}`);
      setDrivers(response.data);
    } catch (error) {
      setError("Failed to fetch drivers.");
      console.error(error);
    }
  };

  const fetchBuses = async () => {
    try {
      const response = await axios.get("http://localhost:8080/bus/get");
      setBuses(response.data);
    } catch (error) {
      setError("Failed to fetch buses.");
      console.error(error);
    }
  };

  const handleAddDriver = async () => {
    if (!driverName || !driverNo || !selectedBus) {
      setError("All fields (Driver Name, Driver No, and Bus) must be filled.");
      return;
    }

    const newDriver = {
      driverName,
      driverNo,
      busNo: { busNo: selectedBus },
    };

    try {
      await axios.post("http://localhost:8080/driver/add", newDriver);
      setError("");
      setDriverName("");
      setDriverNo("");
      setSelectedBus("");
      fetchDrivers(); // Fetch updated driver list from backend
    } catch (error) {
      setError("Failed to add driver: " + (error.response?.data?.message || error.message));
      console.error(error);
    }
  };

  const handleDeleteDriver = async () => {
    if (!driverNoToDelete) {
      setError("Please enter a Driver No to delete.");
      return;
    }

    try {
      const response = await axios.delete(`http://localhost:8080/driver/Delete/${driverNoToDelete}`);
      if (response.status === 204) {
        setError(""); // Clear error message
        setDriverNoToDelete(""); // Clear input field
        fetchDrivers(); // Fetch updated driver list
        alert("Driver deleted successfully!"); // Show success message
      }
    } catch (error) {
      setError("Failed to delete driver. It may already be deleted.");
      console.error("Error deleting driver:", error);
    }
  };

  const handleEditDriver = (driver) => {
    setDriverName(driver.driverName);
    setDriverNo(driver.driverNo);
    setSelectedBus(driver.busNo ? driver.busNo.busNo : "");
    setIsEditing(true);
    setEditingDriverId(driver.driverId);
  };

  const handleUpdateDriver = async () => {
    if (!driverName && !driverNo && !selectedBus) {
      setError("At least one field (Driver Name, Driver No, or Bus) must be updated.");
      return;
    }

    const updatedDriver = {
      driverName: driverName || undefined,
      driverNo: driverNo || undefined,
      busNo: selectedBus ? { busNo: selectedBus } : undefined,
    };

    try {
      await axios.put(`http://localhost:8080/driver/update/${editingDriverId}`, updatedDriver);
      setError("");
      setDriverName("");
      setDriverNo("");
      setSelectedBus("");
      setIsEditing(false);
      setEditingDriverId(null);
      fetchDrivers(); // Fetch updated driver list from backend
    } catch (error) {
      setError("Failed to update driver: " + (error.response?.data?.message || error.message));
      console.error(error);
    }
  };

  return (
    <div>
      <h2>Driver Management</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}

      <h3>{isEditing ? "Edit Driver" : "Add Driver"}</h3>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Driver Name</th>
            <th>Driver No</th>
            <th>Bus</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
              <Form.Control
                type="text"
                value={driverName}
                onChange={(e) => setDriverName(e.target.value)}
                placeholder="Enter Driver Name"
              />
            </td>
            <td>
              <Form.Control
                type="number"
                value={driverNo}
                onChange={(e) => setDriverNo(e.target.value)}
                placeholder="Enter Driver No"
              />
            </td>
            <td>
              <Form.Select value={selectedBus} onChange={(e) => setSelectedBus(e.target.value)}>
                <option value="">Select Bus</option>
                {buses.length > 0 ? (
                  buses.map((bus) => (
                    <option key={bus.busNo} value={bus.busNo}>
                      {bus.busNo} - {bus.busName}
                    </option>
                  ))
                ) : (
                  <option value="">No buses available</option>
                )}
              </Form.Select>
            </td>
            <td>
              <Button variant="success" onClick={isEditing ? handleUpdateDriver : handleAddDriver}>
                <FaPlus /> {isEditing ? "Update Driver" : "Add Driver"}
              </Button>
            </td>
          </tr>
        </tbody>
      </Table>

      <h3>Delete Driver</h3>
      <Form.Control
        type="number"
        value={driverNoToDelete}
        onChange={(e) => setDriverNoToDelete(e.target.value)}
        placeholder="Enter Driver ID to delete"
      />
      <Button variant="danger" onClick={handleDeleteDriver}>
        <FaTrash /> Delete Driver
      </Button>

      <h3>Driver List</h3>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Driver No</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {drivers.map((driver) => (
            <tr key={driver.driverId}>
              <td>{driver.driverId}</td>
              <td>{driver.driverName}</td>
              <td>{driver.driverNo}</td>
              <td>
                <Button variant="warning" onClick={() => handleEditDriver(driver)}>
                  <FaEdit />
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default DriverManagement;
