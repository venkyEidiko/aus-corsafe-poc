import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import axios from 'axios';

function ReviewAudit() {
  const token = useSelector((state) => state.auth.jwtToken);
  const [agreements, setAgreements] = useState({
    framework: false,
    profileAccess: false,
    termsAndConditions: false
  });

  const handleChange = (event) => {
    const { name, checked } = event.target;
    setAgreements((prev) => ({ ...prev, [name]: checked }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    // Ensure all checkboxes are checked
    if (!Object.values(agreements).every(Boolean)) {
      alert('Please agree to all terms before submitting.');
      return;
    }

    if (!token) {
      alert('Authorization token is missing. Please log in again.');
      return;
    }

    // Prepare the data for the API request
    const requestData = {
      taskId: 6755399442995549,
      variables: [
        { name: "agreeToFrameWork", value: agreements.framework },
        { name: "agreeToProfileAccess", value: agreements.profileAccess },
        { name: "agreeToTC", value: agreements.termsAndConditions }
      ]
    };

    try {
      // Send POST request using axios
      const response = await axios.post(
        "http://localhost:8086/auditRequest/completeTask1",
        requestData,
        {
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
          }
        }
      );

      // Handle successful response
      alert("Form submitted successfully!");
      console.log("Response:", response.data);

    } catch (error) {
      // Handle error response
      console.error("Error submitting form:", error);
      alert("Failed to submit form. Please try again.");
    }
  };

  return (
    <div style={{ maxWidth: '400px', margin: '0 auto', padding: '20px', border: '1px solid #ddd', borderRadius: '8px', boxShadow: '0px 0px 8px rgba(0,0,0,0.1)' }}>
      <h3>Terms and Conditions</h3>
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '10px' }}>
          <label>
            <input type="checkbox" name="framework" checked={agreements.framework} onChange={handleChange} />
            Please agree to framework
          </label>
        </div>

        <div style={{ marginBottom: '10px' }}>
          <label>
            <input type="checkbox" name="profileAccess" checked={agreements.profileAccess} onChange={handleChange} />
            Agree to access your profile by auditor
          </label>
        </div>

        <div style={{ marginBottom: '10px' }}>
          <label>
            <input type="checkbox" name="termsAndConditions" checked={agreements.termsAndConditions} onChange={handleChange} />
            Agree to Terms & Conditions
          </label>
        </div>

        <button type="submit" style={{ padding: '8px 16px', background: '#4CAF50', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>Submit</button>
      </form>
    </div>
  );
}

export default ReviewAudit;
