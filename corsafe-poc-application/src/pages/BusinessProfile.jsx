import { Grid, Box, Typography, Card, CardContent, Button } from '@mui/material';
import React, { useState } from 'react';
import axios from 'axios'; 
import '../assets/styles/businessprofile.css';
import CircleOutlinedIcon from '@mui/icons-material/CircleOutlined';
import Checkbox from '@mui/material/Checkbox';
import checklistdata from '../data/checklist.json';
import checklist2data from '../data/checklist2.json';

const BusinessProfile = () => {
  const label = { inputProps: { 'aria-label': 'Checkbox demo' } };

 
  const [selectedItems, setSelectedItems] = useState({
    checklist1: [],
    checklist2: [],
  });

  const handleCheckboxChange = (list, title) => {
    setSelectedItems(prevState => {
      const newList = prevState[list].includes(title)
        ? prevState[list].filter(item => item !== title)
        : [...prevState[list], title];
      return { ...prevState, [list]: newList };
    });
  };

  
  const handleSubmit = async () => {
    
    let payload = {
      title: "",
      description: ""
    };

   
    checklistdata.forEach(item => {
      if (selectedItems.checklist1.includes(item.title)) {
        payload = { title: item.title, description: item.description };
      }
    });
  
    
    checklist2data.forEach(item => {
      if (selectedItems.checklist2.includes(item.title1)) {
        payload = { title: item.title1, description: item.description1 };
      }
    });

    try {
      const response = await axios.post('http://localhost:5000/api/e94af90e-b94e-4218-ab73-fc2ce8a60957/inbound/4445', payload, {
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
            <p>
              Please complete the below steps by answering all the questions to help us onboard you.
            </p>
            <div className='list-items'>      
              <div className='items'>
                <CircleOutlinedIcon fontSize='large' />
                <Typography className='numbers'>1</Typography>
                <p>Supply Chain Services</p>
              </div>
              <div className='items'>
                <CircleOutlinedIcon fontSize='large' />
                <Typography className='numbers'>2</Typography>
                <p>Carrier Business Insight</p>
              </div>
              <div className='items'>
                <CircleOutlinedIcon fontSize='large' />
                <Typography className='numbers'>3</Typography>
                <p>Goals & Objectives</p>
              </div>
            </div>
          </Box>
        </Grid>

        <Grid item xs={12} md={7} lg={9} sx={{ padding: 3 }}>
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
                          <Checkbox
                            {...label}
                            checked={selectedItems.checklist1.includes(item.title)}
                            onChange={() => handleCheckboxChange('checklist1', item.title)}
                          />
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
                          <Checkbox
                            {...label}
                            checked={selectedItems.checklist2.includes(item.title1)}
                            onChange={() => handleCheckboxChange('checklist2', item.title1)}
                          />
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
            <Button sx={{ color: '#e0e0e0', backgroundColor: "#9e9e9e" }}>Back</Button>
            <Button 
              sx={{ backgroundColor: "#7cb342", color: 'white' }} 
              onClick={handleSubmit}
            >
              Continue
            </Button>
          </div>
        </Grid>
      </Grid>
    </div>
  );
}

export default BusinessProfile;
