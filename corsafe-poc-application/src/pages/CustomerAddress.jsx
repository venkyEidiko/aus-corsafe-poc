import { Button, Card, CardContent, TextField } from '@mui/material';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { postCustomerAddress } from '../actions/customerAddresAction';

const CustomerAddress = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [formData, setFormData] = useState({
    fullName: '',
    phoneNumber: '',
    state: '',
    pincode: '',
    city: '',
    houseNumber: '',
    street: ''
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(postCustomerAddress(formData)); 
    navigate('/allProducts/payment'); 
  };

  return (
    <div>
      <Card sx={{ height: '100vh', backgroundColor: '#f5f5f5' }}>
        <CardContent>
          <p style={{ fontSize: '24px', margin: '20px' }}>Please fill in all details</p>
          <form onSubmit={handleSubmit}>
            <TextField
              label="Full Name"
              name="fullName"
              value={formData.fullName}
              onChange={handleChange}
              fullWidth
              variant="standard"
            />
            <TextField
              label="Phone Number"
              name="phoneNumber"
              value={formData.phoneNumber}
              onChange={handleChange}
              fullWidth
              variant="standard"
            />
            <div style={{ display: 'flex', flexDirection: 'row', gap: 20 }}>
              <TextField
                label="State"
                name="state"
                value={formData.state}
                onChange={handleChange}
                fullWidth
                variant="standard"
              />
              <TextField
                label="Pincode"
                name="pincode"
                value={formData.pincode}
                onChange={handleChange}
                fullWidth
                variant="standard"
              />
            </div>
            <TextField
              label="City"
              name="city"
              value={formData.city}
              onChange={handleChange}
              fullWidth
              variant="standard"
            />
            <TextField
              label="House No, Building Name"
              name="houseNumber"
              value={formData.houseNumber}
              onChange={handleChange}
              fullWidth
              variant="standard"
            />
            <TextField
              label="Street No, Area"
              name="street"
              value={formData.street}
              onChange={handleChange}
              fullWidth
              variant="standard"
            />
            <Button type="submit" variant="contained" sx={{ marginTop: '20px' }}>Proceed to Payment</Button>
          </form>
        </CardContent>
      </Card>
    </div>
  );
};

export default CustomerAddress;
