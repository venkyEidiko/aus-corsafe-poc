package com.aus.corsafe.service;

import com.aus.corsafe.config.ApplicationConfig;
import com.aus.corsafe.entity.Cart;
import com.aus.corsafe.entity.CartItem;
import com.aus.corsafe.entity.Products;
import com.aus.corsafe.entity.UserRegister;
import com.aus.corsafe.exceptions.ProductNotFoundException;
import com.aus.corsafe.exceptions.UserNotFoundExceptionCls;
import com.aus.corsafe.repository.CartRepository;
import com.aus.corsafe.repository.ProductRepository;
import com.aus.corsafe.repository.UserRegisterRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRegisterRepo userRegisterRepo;

    @Autowired
    private ProductRepository productRepository;


    /**
     * public List<Cart> addToCart(List<Integer> productIds, Integer userId) {
     * List<Cart> cartItems = new ArrayList<>();
     * <p>
     * // Check if the user exists
     * Optional<UserRegister> userOptional = userRegisterRepo.findById(userId);
     * if (!userOptional.isPresent()) {
     * throw new RuntimeException(ApplicationConfig.USER_NOT_FOUND + userId);
     * }
     * <p>
     * for (Integer productId : productIds) {
     * Optional<Products> productOptional = productRepository.findById(productId);
     * if (!productOptional.isPresent()) {
     * throw new RuntimeException(ApplicationConfig.PRODUCT_NOT_FOUND + productId);
     * }
     * <p>
     * Products product = productOptional.get();
     * <p>
     * // Create a new cart item for each product with quantity set to 1
     * Cart cart = new Cart();
     * cart.setProduct(product);
     * cart.setQuantity(1);
     * cart.setUserId(userId);
     * cart.setPrice(product.getPrice().longValue());  // Set individual product price
     * cart.setTotalPrice(cart.getPrice());
     * cartItems.add(cartRepository.save(cart));
     * }
     * <p>
     * return cartItems;
     * }
     */

    /*adding product to the cart */
    public Cart addProductToUserCart(Integer userId, Integer productId, Integer quantity) throws ProductNotFoundException {

        log.info("addProductToUserCart entered, userId is: {}", userId);

        if(userId==null){
            throw new UserNotFoundExceptionCls("usernot found"+userId);
        }


        userRegisterRepo.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundExceptionCls("User not found with ID: " + userId));

        log.info("user id is: {}", userId);

        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart(userId));
        log.info("cart details {}", cart);


        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            log.info("product already there: {}", existingItem.isPresent());
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            log.info("else block means cart not there for user... ");
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setPrice((double) product.getPrice());

            cart.getItems().add(newItem);
        }

        cart.updateTotalPrice();
        return cartRepository.save(cart);


    }



    public Cart removeItemFromUserCart(Integer userId, Integer productId, Integer quantityToRemove) throws ProductNotFoundException {
        log.info("removeItemFromUserCart entered, userId: {}, productId: {}, quantityToRemove: {}", userId, productId, quantityToRemove);

        // Validate user existence
        userRegisterRepo.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundExceptionCls("User not found with ID: " + userId));

        // Retrieve the user's cart
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ProductNotFoundException("Cart not found for user ID: " + userId));

        // Find the item to be updated or removed
        Optional<CartItem> itemToModify = cart.getItems().stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst();

        if (itemToModify.isPresent()) {
            CartItem cartItem = itemToModify.get();
            log.info("Current quantity: {}, quantity to remove: {}", cartItem.getQuantity(), quantityToRemove);

            // Check if quantity to remove is less than or equal to the current quantity
            if (cartItem.getQuantity() > quantityToRemove) {
                cartItem.setQuantity(cartItem.getQuantity() - quantityToRemove);
            } else {
                // Remove item from cart if quantity becomes zero or less
                cart.getItems().remove(cartItem);
                log.info("Item quantity reduced to zero, removing item from cart");
            }

            // Update the cart's total price
            cart.updateTotalPrice();
            return cartRepository.save(cart);
        } else {
            throw new ProductNotFoundException("Product not found in the cart");
        }
    }



    public Cart addQuantityToUserCart(Integer userId, Integer productId, Integer quantityToAdd) throws ProductNotFoundException {
        log.info("addQuantityToUserCart called with userId: {}, productId: {}, quantityToAdd: {}", userId, productId, quantityToAdd);

        // Validate user existence
        userRegisterRepo.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundExceptionCls("User not found with ID: " + userId));

        // Retrieve or create a new cart for the user
        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart(userId));

        // Find the cart item
        Optional<CartItem> itemToModify = cart.getItems().stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst();

        if (itemToModify.isPresent()) {
            CartItem cartItem = itemToModify.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantityToAdd); // Add quantity
            cartItem.setPrice(cartItem.getProduct().getPrice()); // Ensure price is updated
        } else {
            // If product is not in cart, add it as a new item
            Products product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantityToAdd);
            newItem.setPrice(product.getPrice());

            cart.getItems().add(newItem);
        }

        cart.updateTotalPrice(); // Update total price in cart
        return cartRepository.save(cart);
    }

    public Cart removeQuantityFromUserCart(Integer userId, Integer productId, Integer quantityToRemove) throws ProductNotFoundException {
        log.info("removeQuantityFromUserCart called with userId: {}, productId: {}, quantityToRemove: {}", userId, productId, quantityToRemove);

        // Validate user existence
        userRegisterRepo.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundExceptionCls("User not found with ID: " + userId));

        // Retrieve the cart
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ProductNotFoundException("Cart not found for user ID: " + userId));

        // Find the cart item
        Optional<CartItem> itemToModify = cart.getItems().stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst();

        if (itemToModify.isPresent()) {
            CartItem cartItem = itemToModify.get();
            if (cartItem.getQuantity() > quantityToRemove) {
                cartItem.setQuantity(cartItem.getQuantity() - quantityToRemove); // Decrease quantity
            } else {
                // Remove item if quantity goes to zero or below
                cart.getItems().remove(cartItem);
            }
        } else {
            throw new ProductNotFoundException("Product not found in the cart");
        }

        cart.updateTotalPrice(); // Update total price in cart
        return cartRepository.save(cart);
    }


    public List<CartItem> getCartItemsByCartId(Long cartId) throws ProductNotFoundException {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ProductNotFoundException("Cart not found with ID: " + cartId));
        return cart.getItems();
    }








/**
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
 */
}
