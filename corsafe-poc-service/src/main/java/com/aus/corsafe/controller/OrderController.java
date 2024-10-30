package com.aus.corsafe.controller;

import com.aus.corsafe.dto.OrderDto;
import com.aus.corsafe.entity.Cart;
import com.aus.corsafe.entity.Order;
import com.aus.corsafe.entity.UserRegister;
import com.aus.corsafe.repository.CartRepository;
import com.aus.corsafe.repository.UserRegisterRepo;
import com.aus.corsafe.service.OrderService;
import com.razorpay.RazorpayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRegisterRepo userRepo;

    @Autowired
    private CartRepository cartRepo;

    @PostMapping("/placeorder")
    public ResponseEntity<String> plcaeOrder(@RequestBody OrderDto dto) throws RazorpayException {

        log.info("placeoreder controller entered}");
        log.info(" userid: {}, cartid:{}", dto.getUserId(),dto.getCartId());

        Optional<UserRegister> userOptional = userRepo.findByUserId(dto.getUserId());
        Optional<Cart> cartOptional = cartRepo.findByCartId(dto.getCartId());
        log.info("user is present: {},  cartid present: {}",userOptional.isPresent(),cartOptional.isPresent());
        if (userOptional.isPresent() && cartOptional.isPresent()) {
            UserRegister user = userOptional.get();
            Cart cart = cartOptional.get();
            log.info(" placeorder controller if block");
            Order order = orderService.placeOrder(cart, user, dto.getAdress(), dto.getCity(), dto.getPostalcode(), dto.getArea());
            return ResponseEntity.ok("order create sucessfully");  // Return the created order
        } else {
            log.info(" placeorder controller else  block");
            // If either the user or cart is not found, return an error response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Cart not found");
        }


    }
}
