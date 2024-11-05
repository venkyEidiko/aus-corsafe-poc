import { Button, Card, CardContent, Grid, Tooltip } from '@mui/material';
import React from 'react'
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom'
import '../assets/styles/AddToCart.css'
import CloseSharpIcon from '@mui/icons-material/CloseSharp';
import RemoveSharpIcon from '@mui/icons-material/RemoveSharp';
import AddSharpIcon from '@mui/icons-material/AddSharp';
import { deleteCartItem } from '../slice/DeleteCartitem';
import { deleteFromCart } from '../slice/CartSlice';

const AddToCart = () => {
  const dispatch = useDispatch();
  const userId = useSelector((state) => state.auth.userDetails?.userId);
  const token = useSelector((state) => state.auth.jwtToken);
  const cartItems = useSelector((state) => state.cart.items);
  const navigate = useNavigate();

  const handleClick = () => {
    navigate('/allProducts/address'); 
  };

  const deleteToCart = (item) => {
    const { productId, stockQuantity ,id} = item;
    if (!productId || !userId || !token) {
      console.error("Product ID, User ID, or token is missing!");
      return;
    }

    
    console.log("Deleting from cart - User ID:", userId, "Product ID:", productId, "Stock Quantity:", stockQuantity, "Token:", token);
    dispatch(deleteCartItem({ userId, productId, stockQuantity, token }));
    dispatch(deleteFromCart(id));
  };

  return (
    <div className='addTocart-container'>
      <Card sx={{ height: '100vh', backgroundColor: '#f5f5f5' }}>
        <CardContent>
          <h2>Shopping Cart</h2>
          {cartItems.length === 0 ? (
            <p>Your cart is empty</p>
          ) : (
            <Grid container spacing={2} sx={{ display: 'flex', flexDirection: 'column' }}>
              {cartItems.map((item, index) => (
                <Grid item xs={12} sm={6} md={4} key={index}>
                  <Card sx={{ width: '440px', height: '100px' }}>
                    <CardContent>
                      <div className='cart-container'>
                        <div className='img-container'>
                          <img src={`data:image/jpeg;base64,${item.productImage}`} alt={item.title} className='cart-img' />
                          <div className='quantity'>
                            <span><RemoveSharpIcon /></span>
                            <span className='quantity-number'>{item.stockQuantity}</span>
                            <span><AddSharpIcon/></span>
                          </div>
                        </div>
                        <div className='headings'>
                          <p>{item.name}</p>
                          <h4>{`Rs. ${item.price}`}</h4>
                        </div>  
                        <span
                          style={{ color: 'red' }}
                          onClick={() => deleteToCart(item)} 
                        >
                          <Tooltip title="Remove item">
                            <CloseSharpIcon />
                          </Tooltip>
                        </span>
                      </div>                     
                    </CardContent>
                  </Card>
                </Grid>
              ))}
            </Grid>
          )}
          <Button onClick={handleClick}>Continue</Button>
        </CardContent>
      </Card>
    </div>
  );
}

export default AddToCart
