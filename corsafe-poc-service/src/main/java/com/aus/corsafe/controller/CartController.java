package com.aus.corsafe.controller;

import com.aus.corsafe.config.ApplicationConfig;
import com.aus.corsafe.dto.CartAddDto;
import com.aus.corsafe.entity.*;
import com.aus.corsafe.exceptions.ProductNotFoundException;
import com.aus.corsafe.repository.CartRepository;
import com.aus.corsafe.repository.ProductRepository;
import com.aus.corsafe.repository.UserRegisterRepo;
//import com.aus.corsafe.response.CommonResponse;
import com.aus.corsafe.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/cart")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartController {


    @Autowired
    private UserRegisterRepo userRegisterRepo;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    @PostMapping("/cartadd")
    public ResponseEntity<Cart> addProductToCart(@RequestBody CartAddDto cartAddDto) {
        try {
            log.info("userId: {}, ProductId: {}, quantity: {} - completed",
                    cartAddDto.getUserId(), cartAddDto.getProductId(), cartAddDto.getQuantity());

            Cart updatedCart = cartService.addProductToUserCart(cartAddDto.getUserId(), cartAddDto.getProductId(), cartAddDto.getQuantity());
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            log.info("catch block ProductNotFound ", e.toString());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Product not found
        } catch (Exception e) {
            log.info("catch block Exception ", e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Handle other exceptions
        }

    }


    @PostMapping("/removeItem")
    public ResponseEntity<Cart> removeItemFromCart(@RequestBody CartAddDto cartAddDto) {
        try {
            log.info("Removing ProductId: {} for userId: {}, quantity: {}", cartAddDto.getProductId(), cartAddDto.getUserId(), cartAddDto.getQuantity());

            Cart updatedCart = cartService.removeItemFromUserCart(cartAddDto.getUserId(), cartAddDto.getProductId(), cartAddDto.getQuantity());
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            log.info("ProductNotFoundException: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Product not found
        } catch (Exception e) {
            log.info("Exception: {}", e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Handle other exceptions
        }
    }


    @PostMapping("/addQuantity")
    public ResponseEntity<Cart> addQuantityToItem(@RequestBody CartAddDto cartAddDto) {
        try {
            log.info("Adding quantity for ProductId: {} in userId: {}", cartAddDto.getProductId(), cartAddDto.getUserId());

            // Call the service method with a quantity of 1
            Cart updatedCart = cartService.addQuantityToUserCart(cartAddDto.getUserId(), cartAddDto.getProductId(), 1);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            log.info("ProductNotFoundException: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Product not found
        } catch (Exception e) {
            log.info("Exception: {}", e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Handle other exceptions
        }
    }

    @PostMapping("/removeQuantity")
    public ResponseEntity<Cart> removeQuantityFromItem(@RequestBody CartAddDto cartAddDto) {
        try {
            log.info("Removing quantity for ProductId: {} in userId: {}", cartAddDto.getProductId(), cartAddDto.getUserId());

            // Call the service method with a quantity of 1
            Cart updatedCart = cartService.removeQuantityFromUserCart(cartAddDto.getUserId(), cartAddDto.getProductId(), 1);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            log.info("ProductNotFoundException: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Product not found
        } catch (Exception e) {
            log.info("Exception: {}", e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Handle other exceptions
        }
    }



    @GetMapping("/cartitems/{cartId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long cartId) {
        try {
            List<CartItem> cartItems = cartService.getCartItemsByCartId(cartId);
            if (cartItems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No items found in the cart
            }
            return new ResponseEntity<>(cartItems, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error retrieving cart items for cartId {}: {}", cartId, e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }








/*
    // API to add product to cart
    @PostMapping("/add")
    public ResponseEntity<ResponseModel<Object>> addToCart(@RequestParam List<Integer> productIds,
                                                           @RequestParam Integer userId) {
        List<Cart> cartItems;
        log.info("---------{}",userId);
        try {
            cartItems = cartService.addToCart(productIds, userId);
        } catch (RuntimeException e) {
            return new CommonResponse<>().prepareErrorResponseObject(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new CommonResponse<>().prepareSuccessResponseObject(cartItems, HttpStatus.OK);
    }



    // API to update the quantity of a cart item
    @PutMapping("/updateQuantity/{cartId}")
    public ResponseEntity<ResponseModel<Object>> updateCartItemQuantity(@PathVariable Long cartId,
                                                                        @RequestParam Integer newQuantity) {
        Cart updatedCartItem;
        try {
            updatedCartItem = cartService.updateCartItemQuantity(cartId, newQuantity);
        } catch (RuntimeException e) {
            return new CommonResponse<>().prepareErrorResponseObject(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new CommonResponse<>().prepareSuccessResponseObject(updatedCartItem, HttpStatus.OK);
    }







    // API to get cart items using userid
    @GetMapping("/items")
    public ResponseEntity<ResponseModel<Object>> getCartItems(@RequestParam Integer userId) {
        if (!cartService.userExists(userId)) {
            return new CommonResponse<>().prepareErrorResponseObject(ApplicationConfig.USER_NOT_FOUND + userId, HttpStatus.NOT_FOUND);
        }

        List<Cart> cartItems = cartService.getCartItems(userId);
        return new CommonResponse<>().prepareSuccessResponseObject(cartItems, HttpStatus.OK);
    }


    //this api is used for removing the cartitem by cartid and userid
    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<ResponseModel<Object>> removeCartItem(@PathVariable Long cartId, @RequestParam Integer userId) {
        // Check if the user exists
        if (!cartService.userExists(userId)) {
            return new CommonResponse<>().prepareErrorResponseObject(ApplicationConfig.USER_NOT_FOUND + userId, HttpStatus.NOT_FOUND);
        }

        // Check if the cart item exists
        if (!cartService.cartItemExists(cartId)) {
            return new CommonResponse<>().prepareErrorResponseObject(ApplicationConfig.CART_ITEM_NOT_FOUND + cartId, HttpStatus.NOT_FOUND);
        }

        cartService.removeCartItem(cartId);
        return new CommonResponse<>().prepareSuccessResponseObject(ApplicationConfig.CART_ITEM_REMOVED, HttpStatus.OK);
    }

*/
}
