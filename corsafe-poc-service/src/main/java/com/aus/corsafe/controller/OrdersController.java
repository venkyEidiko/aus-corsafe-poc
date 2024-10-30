//package com.aus.corsafe.controller;
//import com.aus.corsafe.entity.Cart;
//import com.aus.corsafe.entity.Orders;
//import com.aus.corsafe.entity.ResponseModel;
//import com.aus.corsafe.repository.OrdersRepository;
//import com.aus.corsafe.repository.PaymentRepo;
//import com.aus.corsafe.response.CommonResponse;
//import com.aus.corsafe.service.OrderService;
//import com.razorpay.Payment;
//import lombok.extern.slf4j.Slf4j;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//@Controller
//@CrossOrigin(origins="*")
//@RequestMapping("/orderdetails")
//public class OrderController {
//    @Autowired
//    OrderService orderService;
//
//    @Autowired
//    private OrdersRepository orderRepo;
//
//
//    @Autowired
//    private PaymentRepo paymentRepo;
//
//    @PostMapping(value = "/createOrder", produces = "application/json")
//    public ResponseEntity<ResponseModel<Object>> createOrder(@RequestBody Orders orders) {
//        Orders createdOrder;
//        try {
//            createdOrder = orderService.createOrder(orders);
//        } catch (Exception e) {
//            log.error("Error creating order: {}", e.getMessage());
//            return new CommonResponse<>().prepareErrorResponseObject(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//        return new CommonResponse<>().prepareSuccessResponseObject(createdOrder, HttpStatus.CREATED);
//    }
//
//
//    @GetMapping("/")
//    public String init()
//    {
//        return "index";
//    }
//
//
//
//
//    @GetMapping("/paymentDetails/{paymentId}")
//    public ResponseEntity<ResponseModel<Object>> getPaymentDetails(@PathVariable String paymentId) {
//        Payment paymentDetails;
//        try {
//            paymentDetails = orderService.getPaymentDetails(paymentId);
//        } catch (Exception e) {
//            log.error("Error fetching payment details: {}", e.getMessage());
//            return new CommonResponse<>().prepareErrorResponseObject("Payment details not found", HttpStatus.NOT_FOUND);
//        }
//        return new CommonResponse<>().prepareSuccessResponseObject(paymentDetails, HttpStatus.OK);
//    }
//
//
//
//    //this api is used to update the order status by razorpay order id
//    @PostMapping("/updateOrderStatus")
//    @ResponseBody
//    public ResponseEntity<ResponseModel<Object>> updateOrderStatus(@RequestBody Map<String, String> request) {
//        String orderId = request.get("orderId");
//        String status = request.get("status");
//
//        Orders order = orderRepo.findByRazorPayOrderId(orderId);
//        if (order != null) {
//            order.setOrderStatus(status);
//            orderRepo.save(order);
//            return new CommonResponse<>().prepareSuccessResponseObject("Order status updated", HttpStatus.OK);
//        } else {
//            return new CommonResponse<>().prepareErrorResponseObject("Order not found", HttpStatus.NOT_FOUND);
//        }
//    }
//
//
////    @PostMapping("/webhook/razorpay")
////    @ResponseBody
////    public ResponseEntity<String> handlePaymentStatusUpdate(
////            @RequestBody String payload,
////            @RequestHeader("X-Razorpay-Signature") String razorpaySignature) {
////        try {
////            // Verify webhook signature
////            OrderService webhookService = new OrderService();
////            String secret = "your-razorpay-webhook-secret";
////            boolean isValidSignature = webhookService.verifyWebhookSignature(payload, razorpaySignature, secret);
////
////            if (!isValidSignature) {
////                return new ResponseEntity<>("Invalid webhook signature", HttpStatus.UNAUTHORIZED);
////            }
////
////            JSONObject webhookPayload = new JSONObject(payload);
////            String event = webhookPayload.getString("event");
////            JSONObject paymentDetails = webhookPayload.getJSONObject("payload").getJSONObject("payment").getJSONObject("entity");
////
////            String razorpayOrderId = paymentDetails.getString("order_id");
////            String paymentStatus = paymentDetails.getString("status");
////
////            if ("payment.captured".equals(event)) {
////                Orders order = orderRepo.findByRazorPayOrderId(razorpayOrderId);
////
////                if (order != null) {
////                    // Update order details
////                    order.setOrderStatus(paymentStatus);
////                    order.setRazorPayPaymentId(paymentDetails.getString("id"));
////                    order.setPaymentMethod(paymentDetails.getString("method"));
////                    order.setCurrency(paymentDetails.getString("currency"));
////                    order.setAmount(paymentDetails.getInt("amount") / 100); // Amount in smallest unit, dividing by 100
////
////                    // Set additional details based on payment method
////                    if (paymentDetails.has("bank")) {
////                        order.setBank(paymentDetails.getString("bank"));
////                    }
////                    if (paymentDetails.has("wallet")) {
////                        order.setWallet(paymentDetails.getString("wallet"));
////                    }
////                    if (paymentDetails.has("vpa")) {
////                        order.setVpa(paymentDetails.getString("vpa"));
////                    }
////
////                    // Save updated order details to the database
////                    orderRepo.save(order);
////
////                    return new ResponseEntity<>("Order details updated successfully", HttpStatus.OK);
////                } else {
////                    return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
////                }
////            }
////
////            return new ResponseEntity<>("Unhandled event", HttpStatus.BAD_REQUEST);
////        } catch (Exception e) {
////            return new ResponseEntity<>("Error processing webhook", HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
//}