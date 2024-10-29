package com.aus.corsafe.service;

import com.aus.corsafe.config.ApplicationConfig;
import com.aus.corsafe.entity.Cart;
import com.aus.corsafe.entity.Products;
import com.aus.corsafe.entity.UserRegister;
import com.aus.corsafe.repository.CartRepository;
import com.aus.corsafe.repository.ProductRepository;
import com.aus.corsafe.repository.UserRegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRegisterRepo userRegisterRepo;

    @Autowired
    private ProductRepository productRepository;


     /** */
    public List<Cart> addToCart(List<Integer> productIds, Integer userId) {
        List<Cart> cartItems = new ArrayList<>();

        // Check if the user exists
        Optional<UserRegister> userOptional = userRegisterRepo.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException(ApplicationConfig.USER_NOT_FOUND + userId);
        }

        for (Integer productId : productIds) {
            Optional<Products> productOptional = productRepository.findById(productId);
            if (!productOptional.isPresent()) {
                throw new RuntimeException(ApplicationConfig.PRODUCT_NOT_FOUND + productId);
            }

            Products product = productOptional.get();

            // Create a new cart item for each product with quantity set to 1
            Cart cart = new Cart();
            cart.setProduct(product);
            cart.setQuantity(1);
            cart.setUserId(userId);
            cart.setPrice(product.getPrice().longValue());  // Set individual product price
            cart.setTotalPrice(cart.getPrice());
            cartItems.add(cartRepository.save(cart));
        }

        return cartItems;
    }




    public Cart updateCartItemQuantity(Long cartId, Integer newQuantity) {
        // Check if the cart item exists
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (!cartOptional.isPresent()) {
            throw new RuntimeException("Cart item not found with ID: " + cartId);
        }

        Cart cart = cartOptional.get();

        // Update the quantity and recalculate the total price
        cart.setQuantity(newQuantity);
        cart.setTotalPrice(cart.getPrice() * newQuantity);

        // Save the updated cart item
        return cartRepository.save(cart);
    }


    public List<Cart> getCartItems(Integer userId) {
        return cartRepository.findByUserId(userId);
    }

    public void removeCartItem(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public boolean userExists(Integer userId) {
        return userRegisterRepo.existsById(userId);
    }

    public boolean cartItemExists(Long cartId) {
        return cartRepository.existsById(cartId);
    }
}
