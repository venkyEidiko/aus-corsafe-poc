import axios from 'axios';


export const postCustomerAddress = (formData) => async (dispatch) => {
  try {
    const response = await axios.post('/api/customer/address', formData); 
    dispatch({ type: 'POST_ADDRESS_SUCCESS', payload: response.data });
  } catch (error) {
    dispatch({ type: 'POST_ADDRESS_FAILURE', payload: error.message });
  }
};
