import { createSlice } from '@reduxjs/toolkit';

const cartSlice = createSlice({
  name: 'cart',
  initialState: {
    items: [],
    cartId: null, 
  },
  reducers: {
    addToCart: (state, action) => {
      state.items.push(action.payload);
    },
    deleteFromCart:(state,action)=>{
      state.items = state.items.filter(item => item.id !== action.payload.id);
     
     
    },
    setCartId: (state, action) => {
      state.cartId = action.payload;  // Set cartId from API
    }
  
  },
});

export const { addToCart,deleteFromCart, setCartId  } = cartSlice.actions;

export default cartSlice.reducer;



