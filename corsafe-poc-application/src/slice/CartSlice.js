import { createSlice } from '@reduxjs/toolkit';

const cartSlice = createSlice({
  name: 'cart',
  initialState: {
    items: [],
  },
  reducers: {
    addToCart: (state, action) => {
      state.items.push(action.payload);
    },
    deleteFromCart:(state,action)=>{
      state.items = state.items.filter(item => item.id !== action.payload.id);
      // state.items.pop(action.payload);
     
    }
  
  },
});

export const { addToCart,deleteFromCart } = cartSlice.actions;

export default cartSlice.reducer;



