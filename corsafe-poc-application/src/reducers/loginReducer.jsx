import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';


export const loginUser = createAsyncThunk(
  'auth/loginUser',
  async (loginData, { rejectWithValue }) => {
    try {
      const response = await axios.post('http://localhost:8086/login', loginData);

      console.log("login response: ", response);

      
      const { jwtToken, refreshToken, firstName, lastName, email, phoneNumber, abn, companyName, companyAddress, state, postalCode } = response.data.result[0];
    
      localStorage.setItem('jwtToken', jwtToken);
      localStorage.setItem('refreshToken', refreshToken);
      localStorage.setItem('userDetails', JSON.stringify({ firstName, lastName, email, phoneNumber, abn, companyName, companyAddress, state, postalCode }));
      
      return { jwtToken, refreshToken }; 
    } catch (error) {
      return rejectWithValue(error.response.data);
    }
  }
);

// Create the login slice
const loginSlice = createSlice({
  name: 'auth',
  initialState: {
    jwtToken: localStorage.getItem('jwtToken') || null,
    refreshToken: localStorage.getItem('refreshToken') || null,
    loading: false,
    error: null,
  },
  reducers: {
    logout(state) {
      state.jwtToken = null;
      state.refreshToken = null;
      localStorage.removeItem('jwtToken');
      localStorage.removeItem('refreshToken');
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(loginUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(loginUser.fulfilled, (state, action) => {
        state.loading = false;
        state.jwtToken = action.payload.jwtToken;
        state.refreshToken = action.payload.refreshToken;
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

// Export actions and reducer
export const { logout } = loginSlice.actions;
export default loginSlice.reducer;
