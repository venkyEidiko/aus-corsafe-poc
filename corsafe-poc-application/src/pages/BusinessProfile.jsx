import React from 'react';
import { Grid, Box, Typography, Card, CardContent, Button, Checkbox } from '@mui/material';
import CircleOutlinedIcon from '@mui/icons-material/CircleOutlined';
import axios from 'axios';
import checklistdata from '../data/checklist.json';
import checklist2data from '../data/checklist2.json';
import { useSelector } from 'react-redux';
import '../assets/styles/businessprofile.css';

const BusinessProfile = () => {
  const label = { inputProps: { 'aria-label': 'Checkbox demo' } };
  const loginResponse = useSelector(state => state.auth); 

  const handleSubmit = async () => {
   
    if (!loginResponse || !loginResponse.jwtToken) {
      console.error('No login data available');
      return;
    }

    const userDetails = JSON.parse(localStorage.getItem('userDetails')) || {};
    
    
    const {
      firstName = "",
      lastName = "",
      email = "",
      phoneNumber = "",
      abn = "",
      companyName = "",
      companyAddress = "",
      state = "",
      postalCode = ""
    } = userDetails;

  
    let payload = {
      firstName,
      lastName,
      email,
      phoneNumber,
      abn,
      companyName,
      companyAddress,
      state,
      postalCode
    };

    console.log("Payload to be sent:", payload); 

    try {
      const response = await axios.post('http://localhost:5000/api/c4765f54-b30c-4eba-b09f-2914741db450/inbound/audit-request', payload, {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      console.log('Successfully submitted:', response.data);
    } catch (error) {
      console.error('Error submitting form:', error);
      if (error.response) {
        console.error('Response data:', error.response.data);
        console.error('Response status:', error.response.status);
      } else {
        console.error('Error message:', error.message);
      }
    }
  };

  return (
    <div className='container'>
      <Grid container spacing={2}>
        <Grid item xs={12} md={5} lg={3} sx={{ display: 'flex', alignItems: 'center' }}>
          <Box sx={{ width: '100%', wordWrap: 'break-word', padding: 5 }}>
            <h2>Business Profile</h2>
            <p>Please complete the below steps by answering all the questions to help us onboard you.</p>
            <div className='list-items'>
              {['Supply Chain Services', 'Carrier Business Insight', 'Goals & Objectives'].map((title, index) => (
                <div className='items' key={index}>
                  <CircleOutlinedIcon fontSize='large' />
                  <Typography className='numbers'>{index + 1}</Typography>
                  <p>{title}</p>
                </div>
              ))}
            </div>
          </Box>
        </Grid>

        <Grid item xs={12} md={7} lg={9} sx={{ padding: 3 }}>
          <Button sx={{backgroundColor:"#7cb342",color:'white',marginTop:'10px'}} onClick={handleSubmit}>Start process</Button>
          <Card sx={{ borderRadius: '15px', margin: '20px' }} elevation={0}>
            <CardContent>
              <h1>My Company does the following:</h1>
              <Grid container spacing={2}>
                <Grid item xs={12} md={6} lg={6}>
                  {checklistdata.map((item, index) => (
                    <Card key={index} elevation={0} sx={{
                      backgroundColor: '#e0e0e0',
                      border: '1px solid #bdbdbd',
                      borderRadius: '10px',
                      marginBottom: '10px',
                      '&:hover': {
                        backgroundColor: '#dcedc8',
                        border: '2px solid #aed581'
                      }
                    }}>
                      <CardContent sx={{ padding: 0 }}>
                        <div className='checklist'>
                          <Checkbox {...label} />
                          <div className='details'>
                            <h5>{item.title}</h5>
                            <p>{item.description}</p>
                          </div>
                        </div>
                      </CardContent>
                    </Card>
                  ))}
                </Grid>

                <Grid item xs={12} md={6} lg={6}>
                  {checklist2data.map((item, index) => (
                    <Card key={index} elevation={0} sx={{
                      backgroundColor: '#e0e0e0',
                      border: '1px solid #bdbdbd',
                      borderRadius: '10px',
                      marginBottom: '10px',
                      '&:hover': {
                        backgroundColor: '#dcedc8',
                        border: '2px solid #aed581'
                      }
                    }}>
                      <CardContent sx={{ padding: 0 }}>
                        <div className='checklist'>
                          <Checkbox {...label} />
                          <div className='details'>
                            <h5>{item.title1}</h5>
                            <p>{item.description1}</p>
                          </div>
                        </div>
                      </CardContent>
                    </Card>
                  ))}
                </Grid>
              </Grid>
            </CardContent>
          </Card>

          <div className='btn'>
            <Button sx={{ color: '#e0e0e0', backgroundColor: '#9e9e9e' }}>Back</Button>
            <Button sx={{ backgroundColor: '#7cb342', color: 'white' }}>
              Continue
            </Button>
          </div>
        </Grid>
      </Grid>
    </div>
  );
};

export default BusinessProfile;
