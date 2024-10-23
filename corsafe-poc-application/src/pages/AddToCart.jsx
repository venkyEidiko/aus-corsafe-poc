import { Button, Card, CardContent, Grid } from '@mui/material';
import React from 'react'
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom'
import '../assets/styles/AddToCart.css'

const AddToCart = () => {
  const cartItems = useSelector((state) => state.cart.items);
  const navigate = useNavigate();
   
  const handleClick = () =>{
    navigate('/allProducts/address'); 
  }

  return (
    <div className='addTocart-container'>
      <Card sx={{height:'100vh',backgroundColor:'#f5f5f5'}}>
        <CardContent>
        <h2>Shopping Cart</h2>
       {cartItems.length === 0 ? (
        <p>Your cart is empty</p>
        ) : (
        <Grid container spacing={2} sx={{display:'flex',flexDirection:'column'}}>
            {cartItems.map((item, index) => (
                <Grid item xs={12} sm={6} md={4} key={index}>
                    <Card sx={{width:'440px',height:'100px'}}>
                        <CardContent>
                          <div className='cart-container'>
                            <div>
                            <img src={`data:image/jpeg;base64,${item.productImage}`} alt={item.title} className='cart-img' />
                            </div>
                           <div className='headings'>
                            <p>{item.name}</p>
                            <h4>{`Rs. ${item.price}`}</h4>
                           </div>  
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
  )
}

export default AddToCart
