import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchProducts } from '../slice/ProductSlice';
import { Card, CardContent, Grid, Tooltip } from '@mui/material';
import '../assets/styles/getproducts.css';
import ShoppingCartOutlinedIcon from '@mui/icons-material/ShoppingCartOutlined';
import { Outlet } from 'react-router-dom';
import {addToCart} from '../slice/CartSlice';


const GetProducts = () => {
    const dispatch = useDispatch();
    const { products, status, error } = useSelector((state) => state.products);

    useEffect(() => {
        if (status === 'idle') {
            dispatch(fetchProducts());
        }
    }, [dispatch, status]);

    const handleAddToCart = (product) => {
        dispatch(addToCart(product)); 
    };
    if (status === 'loading') {
        return <p>Loading...</p>;
    }

    if (status === 'failed') {
        return <p>Error: {error}</p>;
    }

    return (
        <div className='product-container'>
            <Grid container spacing={2}>
                <Grid item xs={12} lg={8} className='grid-scroll'>
                    <p className='title'>List of Products</p>
                    <Grid container spacing={2} sx={{padding:5}}>
                    {products.map((product, index) => (
    <Grid item xs={12} sm={6} md={4} key={index}>
        <Card className="products-cards" elevation={0}>
            <CardContent sx={{ padding: 0 }}>
                <div className="image-container">
                    <img 
                        src={`data:image/jpeg;base64,${product.productImage}`} 
                        alt={product.name} 
                    />
                    <span 
                        className="cart-icon" 
                        style={{color: 'black'}}
                        onClick={() => handleAddToCart(product)}
                    >
                        <Tooltip title="Add to Cart">
                            <ShoppingCartOutlinedIcon/>
                        </Tooltip>
                    </span>
                </div>
                <hr />
                <p>{product.name}</p>
                <h4>{`Rs. ${product.price}`}
                    <span>&nbsp;</span>
                    <span style={{ color: "#689f38" }}>(25% off)</span>
                </h4>
            </CardContent>
        </Card>
    </Grid>
))}

                  </Grid>
                </Grid>
                <Grid item xs={12} lg={4}  >
                    <Outlet/>
                </Grid>
            </Grid>
        </div>
    );
};

export default GetProducts;
