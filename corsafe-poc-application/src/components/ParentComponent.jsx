import React, { useState } from 'react';
import { Box, Typography, Button } from '@mui/material';
import Register from './Register';
import Company from './Company';
import { useDispatch, useSelector } from 'react-redux';
import { registerUser } from '../actions/registerActions';
import Grid from '@mui/material/Grid2';
import { useLocation } from 'react-router-dom';

const RegistrationForm = () => {
    const dispatch = useDispatch();
    const { loading, data, error } = useSelector((state) => state.register);
    const location = useLocation();

    const [formData, setFormData] = useState({
        user: {
            firstName: '',
            lastName: '',
            email: '',
            password: '',
            phoneNumber: ''
        },
        company: {
            abn: '',
            companyName: '',
            companyAddress: '',
            state: '',
            postalCode: ''
        }
    });

    const handleChange = (section, field, value) => {
        setFormData(prevState => ({
            ...prevState,
            [section]: {
                ...prevState[section],
                [field]: value
            }
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const combinedData = {
            ...formData.user,
            ...formData.company
        };
        console.log("Combined Form Data: ", combinedData);
        dispatch(registerUser(combinedData)); 
    };

    return (
        <Box sx={{ flexGrow: 1, p: 2 }}>
            <Grid container spacing={4} justifyContent="center">
                <Grid item xs={12} sm={6}>
                    {location.pathname === '/' && (
                        <Register 
                            userData={formData.user} 
                            onUserChange={(field, value) => handleChange('user', field, value)} 
                        />
                    )}
                </Grid>
                {location.pathname === '/company' && ( 
                    <Grid item xs={12} sm={6}>
                        <Company 
                            companyData={formData.company} 
                            onCompanyChange={(field, value) => handleChange('company', field, value)} 
                        />
                        <Box sx={{ display: 'flex', marginLeft: '580px', mt: 2 }}>
                            <Button 
                                variant="contained" 
                                onClick={handleSubmit} 
                                disabled={loading}
                            >
                                {loading ? 'Registering...' : 'Submit'}
                            </Button>
                        </Box>
                    </Grid>
                )}
            </Grid>
            {error && <Typography color="red" align="center">{error}</Typography>}
            {data && <Typography color="green" align="center">Registered successfully!</Typography>}
        </Box>
    );
};

export default RegistrationForm;
