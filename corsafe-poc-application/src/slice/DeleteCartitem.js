import axios from 'axios';
import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
export const deleteCartItem = createAsyncThunk(
    'cart/deleteCartItem',
    async ({ userId, productId,token,stockQuantity}, { rejectWithValue }) => {
        try {
            console.log("Sending to API:", {userId,productId,stockQuantity,token}); 
          
            const response = await axios.post('http://localhost:8086/cart/removeItem',
                {
                    userId,
                    productId,
                    quantity:stockQuantity
                },
                {
                    headers: {
                        'Content-Type': 'application/json',
                       'Authorization': `Bearer ${token}`,
                    }
                    
                }
            );
            console.log("API Response:", response.data); 
            return response.data; 
        } catch (error) {
            console.error("API Error:", error);
            return rejectWithValue(error.response.data);
        }
    }
)

const initialState1 = {
    deleteItem: null,
    error: null,
    status: 'idle' 
};


export const cartSlice2 = createSlice({
    name: 'cart',
    initialState1,
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(deleteCartItem.pending, (state) => {
                state.status = 'loading';
            })
            .addCase(deleteCartItem.fulfilled, (state, action) => {
                state.status = 'succeeded';
                state.addTocart = action.payload;
                state.error = null;
            })
            .addCase(deleteCartItem.rejected, (state, action) => {
                state.status = 'failed';
                state.error = action.payload;
            });
    },
});


