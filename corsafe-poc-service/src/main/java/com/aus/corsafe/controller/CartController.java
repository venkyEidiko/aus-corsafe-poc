package com.aus.corsafe.controller;

import com.aus.corsafe.config.ApplicationConfig;
import com.aus.corsafe.entity.Cart;
import com.aus.corsafe.entity.Products;
import com.aus.corsafe.entity.ResponseModel;
import com.aus.corsafe.entity.UserRegister;
import com.aus.corsafe.repository.CartRepository;
import com.aus.corsafe.repository.ProductRepository;
import com.aus.corsafe.repository.UserRegisterRepo;
import com.aus.corsafe.response.CommonResponse;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartController {


    @Autowired
    private UserRegisterRepo userRegisterRepo;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;



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


}
