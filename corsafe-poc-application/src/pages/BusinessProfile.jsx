import { Grid, Box, Typography, Card, CardContent, Button } from '@mui/material';
import React from 'react';
import '../assets/styles/businessprofile.css';
import CircleOutlinedIcon from '@mui/icons-material/CircleOutlined';
import Checkbox from '@mui/material/Checkbox';
import checklistdata from '../data/checklist.json';
import checklist2data from '../data/checklist2.json'

const BusinessProfile = () => {
  const label = { inputProps: { 'aria-label': 'Checkbox demo' } };

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
            <div className='items' >
              <CircleOutlinedIcon fontSize='large' />
              <Typography className='numbers' >1</Typography>
              <p>Supply Chain Services</p>
            </div>
                     
            <div className='items'>
              <CircleOutlinedIcon fontSize='large' />
              <Typography className='numbers'>2</Typography>
              <p>Carrier Business Insight</p>
            </div>
            
            <div className='items' >
              <CircleOutlinedIcon fontSize='large' />
              <Typography className='numbers'>3</Typography>
              <p>Goals & Objectives</p>
            </div>
          </div>
        </Box>
      </Grid>

      <Grid item xs={12} md={7} lg={9} sx={{ padding: 3 }}>
        <Card sx={{ borderRadius: '15px', margin: '20px'}} elevation={0}>
          <CardContent>
            <h1>My Company does the following:</h1>
            <Grid container spacing={2}>
              <Grid item xs={12} md={6} lg={6}>

                {checklistdata.map((item, index) => (
                  <Card key={index} elevation={0} sx={{
                    backgroundColor: '#e0e0e0',
                    border: '1px solid #bdbdbd',
                    borderRadius: '10px',
                    marginBottom:'10px',
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
                    marginBottom:'10px',
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
        <Button sx={{color:'#e0e0e0',backgroundColor:"#9e9e9e"}}>Back</Button>
        <Button sx={{backgroundColor:"#7cb342",color:'white'}}>Continue</Button>
        </div>
        
      </Grid>
    </Grid>
    </div>
  );
}

export default BusinessProfile;
