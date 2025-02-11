import React, { useState, useEffect } from "react";
import axios from "axios";
import { FaTimes } from "react-icons/fa";

const PaymentManagement = () => {
  const [orderId, setOrderId] = useState("");
  const [paymentId, setPaymentId] = useState("");
  const [signature, setSignature] = useState("");
  const [seatId, setSeatId] = useState(""); // Seat ID for booking
  const [passengerId, setPassengerId] = useState(""); // Passenger ID for creating order
  const [amount, setAmount] = useState(""); // Amount for creating order
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [orders, setOrders] = useState([]); // State to store orders

  // Fetch all orders from backend
  const fetchOrders = async () => {
    try {
      const response = await axios.get("http://localhost:8080/passenger/getAllOrders");
      console.log("Fetched Orders Response:", response.data);  // Log the full response
      setOrders(response.data); // Update the state with fetched orders
    } catch (err) {
      console.error("Error fetching orders:", err);
      setError("Failed to fetch orders.");
    }
  };

  // Fetch orders on component mount
  useEffect(() => {
    fetchOrders();
  }, []);

  const handleCreateOrder = async () => {
    try {
      const orderRequest = {
        passengerId,
        amount,
        seatId,
      };

      console.log("🔄 Sending Create Order Request:", orderRequest);

      const response = await axios.post("http://localhost:8080/passenger/create-order", orderRequest);
      console.log("✅ Backend Response:", response.data);

      if (response.status === 200) {
        setSuccessMessage("Order created successfully. Please proceed with payment.");
        fetchOrders(); // Fetch updated orders after creating a new order
      } else {
        setError("Failed to create order. Please try again.");
      }
    } catch (err) {
      console.error("❌ Error creating order:", err);
      setError("Error creating order. Please try again.");
    }
  };

  const handlePaymentSuccess = async () => {
    try {
      const paymentResponse = {
        paymentId,
        orderId,
        signature,
        seatId,
      };

      console.log("🔄 Sending Payment Response to Backend:", paymentResponse);

      const response = await axios.post("http://localhost:8080/passenger/payment-success", paymentResponse);

      console.log("✅ Backend Response:", response.data);

      if (response.status === 200) {
        setSuccessMessage("Payment verified and seat booked successfully!");
        fetchOrders(); // Fetch updated orders after payment verification
      } else {
        setError("Payment verification failed. Please try again.");
      }
    } catch (err) {
      console.error("❌ Error verifying payment:", err);
      setError("Error verifying payment. Please try again.");
    }
  };

  const handleCancelOrder = async (orderId, seatId) => {
    try {
      if (!orderId || !seatId) {
        setError("Order ID and Seat ID are required for cancellation.");
        return;
      }

      const cancelRequest = { orderId, seatId };

      console.log("🔄 Sending Cancel Order Request to Backend:", cancelRequest);

      const response = await axios.delete("http://localhost:8080/passenger/cancel-order", { data: cancelRequest });

      console.log("✅ Backend Response:", response.data);

      if (response.status === 200) {
        setSuccessMessage("Order cancelled successfully and seat is now available.");
        fetchOrders(); // Fetch updated orders after canceling
      } else {
        setError("Failed to cancel order. Please try again.");
      }
    } catch (err) {
      console.error("❌ Error cancelling order:", err);
      setError("Error cancelling order. Please check backend logs.");
    }
  };

  return (
    <div className="container mt-4">
      <h2>Payment Management</h2>

      {successMessage && <div className="alert alert-success">{successMessage}</div>}
      {error && <div className="alert alert-danger">{error}</div>}

      {/* Create Order Form */}
      <h3>Create Order</h3>
      <div className="form-group">
        <label>Passenger ID</label>
        <input
          type="text"
          className="form-control"
          value={passengerId}
          onChange={(e) => setPassengerId(e.target.value)}
          placeholder="Enter Passenger ID"
        />
      </div>

      <div className="form-group">
        <label>Amount</label>
        <input
          type="number"
          className="form-control"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          placeholder="Enter Amount"
        />
      </div>

      <div className="form-group">
        <label>Seat ID</label>
        <input
          type="text"
          className="form-control"
          value={seatId}
          onChange={(e) => setSeatId(e.target.value)}
          placeholder="Enter Seat ID"
        />
      </div>

      <button onClick={handleCreateOrder} className="btn btn-primary mt-3">
        Create Order
      </button>

      {/* Payment Success Form */}
      <h3 className="mt-5">Payment Success</h3>
      <div className="form-group">
        <label>Payment ID</label>
        <input
          type="text"
          className="form-control"
          value={paymentId}
          onChange={(e) => setPaymentId(e.target.value)}
          placeholder="Enter Payment ID"
        />
      </div>

      <div className="form-group">
        <label>Order ID</label>
        <input
          type="text"
          className="form-control"
          value={orderId}
          onChange={(e) => setOrderId(e.target.value)}
          placeholder="Enter Order ID"
        />
      </div>

      <div className="form-group">
        <label>Signature</label>
        <input
          type="text"
          className="form-control"
          value={signature}
          onChange={(e) => setSignature(e.target.value)}
          placeholder="Enter Signature"
        />
      </div>

      <div className="form-group">
        <label>Seat ID</label>
        <input
          type="text"
          className="form-control"
          value={seatId}
          onChange={(e) => setSeatId(e.target.value)}
          placeholder="Enter Seat ID"
        />
      </div>

      <button onClick={handlePaymentSuccess} className="btn btn-primary mt-3">
        Verify Payment and Book Seat
      </button>

      <h3 className="mt-5">All Orders</h3>
      {orders.length > 0 ? (
        <table className="table table-bordered">
          <thead className="thead-dark">
            <tr>
              <th>Order ID</th>
              <th>Amount</th>
              <th>Payment Method</th>
              <th>Payment Date</th>
              <th>Status</th>
              <th>Passenger Name</th>
              <th>Passenger Email</th>
              <th>Passenger Mobile No</th>
              <th>Actions</th> {/* Removed Seat No column */}
            </tr>
          </thead>
          <tbody>
            {orders.map((order) => (
              <tr key={order.orderId}>
                <td>{order.orderId || "N/A"}</td>
                <td>{order.amount || "N/A"}</td>
                <td>{order.paymentMethod || "N/A"}</td>
                <td>{order.paymentDate || "N/A"}</td>
                <td>{order.status || "N/A"}</td>
                {/* Safe checks for passenger details */}
                <td>{order.passenger ? order.passenger.passengerName : "N/A"}</td>
                <td>{order.passenger ? order.passenger.email : "N/A"}</td>
                <td>{order.passenger ? order.passenger.mobileNo : "N/A"}</td>
                <td>
                  {order.status !== "COMPLETED" && (
                    <button
                      className="btn btn-danger btn-sm"
                      onClick={() => handleCancelOrder(order.orderId, order.passenger?.seat?.seatNo)}
                      title="Cancel Order"
                    >
                      <FaTimes />
                    </button>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No orders found</p>
      )}
    </div>
  );
};

export default PaymentManagement;
