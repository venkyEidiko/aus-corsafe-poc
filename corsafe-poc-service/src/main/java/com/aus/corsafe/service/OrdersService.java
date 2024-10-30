//package com.aus.corsafe.service;
//import com.aus.corsafe.entity.Cart;
//import com.aus.corsafe.entity.CartItem;
//import com.aus.corsafe.entity.Orders;
//import com.aus.corsafe.entity.UserRegister;
//import com.aus.corsafe.repository.CartRepository;
//import com.aus.corsafe.repository.OrdersRepository;
//import com.aus.corsafe.repository.UserRegisterRepo;
//import com.razorpay.Order;
//import com.razorpay.Payment;
//import com.razorpay.RazorpayClient;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.codec.binary.Hex;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//@Service
//public class OrderService {
//
//    @Autowired
//    OrdersRepository orderRepo;
//
//    @Autowired
//    CartRepository cartRepo;
//
//    @Autowired
//    UserRegisterRepo userRegisterRepo;
//
//    @Value("${razorpay.key.id}")
//    private String razorPayKey;
//
//    @Value("${razorpay.secret.key}")
//    private String razorPaySecret;
//
//    private RazorpayClient client;
//
//
//
////    private static final String HMAC_SHA256 = "HmacSHA256";
//
//
////    public OrderService() throws Exception {
////        this.client = new RazorpayClient(razorPayKey, razorPaySecret);
////    }
//
////    public boolean verifyWebhookSignature(String payload, String razorpaySignature, String secret) throws Exception {
////        Mac sha256Hmac = Mac.getInstance(HMAC_SHA256);
////        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), HMAC_SHA256);
////        sha256Hmac.init(secretKey);
////
////        byte[] hash = sha256Hmac.doFinal(payload.getBytes());
////        String expectedSignature = Hex.encodeHexString(hash);
////
////        return expectedSignature.equals(razorpaySignature);
////    }
//
//
//
//    public Orders createOrder(Orders order) throws Exception {
//
//        Optional<UserRegister> userOptional = userRegisterRepo.findById(Integer.parseInt(order.getUserId()));
//        if (userOptional.isEmpty()) {
//            throw new Exception("User ID not found");
//        }
//
//        UserRegister user = userOptional.get();
//        validateOrderDetails(order, user);
//
//        order.setAttempts(0);
//
//        // Fetch user's cart
//        Optional<Cart> cartOptional = cartRepo.findByUserId(Integer.valueOf(order.getUserId()));
//        if (cartOptional.isEmpty()) {
//            throw new Exception("Cart is empty for user: " + order.getUserId());
//        }
//
//        Cart cart = cartOptional.get();
//        List<CartItem> cartItems = cart.getItems();
//
//        // Calculate total amount
//        int totalAmount = 0;
//        for (CartItem cartItem : cartItems) {
//            totalAmount += cartItem.calculateTotalPrice();
//        }
//        order.setAmount(totalAmount);
//        System.out.println("Total Amount in paise: " + (totalAmount * 100));
//
//        // Create Razorpay order request
//        JSONObject orderReq = new JSONObject();
//        orderReq.put("amount", totalAmount * 100); // Razorpay expects amount in paise
//        orderReq.put("currency", "INR");
//        orderReq.put("receipt", order.getEmail());
//
//        this.client = new RazorpayClient(razorPayKey, razorPaySecret);
//        Order razorPayOrder = client.orders.create(orderReq);
//        order.setRazorPayOrderId(razorPayOrder.get("id"));
//        order.setOrderStatus(razorPayOrder.get("status"));
//        order.setOrderDate(new Date());
//
//        order.setCartItems(cartItems); // Set the cart items in the order
//        orderRepo.save(order); // Save the order along with cart items
//        return order;
//    }
//
//
//    private void validateOrderDetails(Orders order, UserRegister user) throws Exception {
//        if (!order.getName().equals(user.getFirstName())) {
//            throw new Exception("Invalid name provided for user ID: " + order.getUserId());
//        }
//        if (!order.getEmail().equals(user.getEmail())) {
//            throw new Exception("Invalid email provided for user ID: " + order.getUserId());
//        }
//        if (!order.getPhoneNumber().equals(String.valueOf(user.getPhoneNumber()))) {
//            throw new Exception("Invalid phone number provided for user ID: " + order.getUserId());
//        }
//    }
//
//
//
//
//
//    public Payment getPaymentDetails(String razorPayPaymentId) throws Exception {
//        Payment payment = client.payments.fetch(razorPayPaymentId);
//        return payment;
//    }
//
//
////    public Orders createOrder(Orders order) throws Exception {
////        JSONObject orderReq = new JSONObject();
////        //orderReq.put("Payment ID", order.getRazorPayPaymentId());
////        orderReq.put("Bank RRN", order.getBank());
////        orderReq.put("Customer detail", order.getPhoneNumber());
////        order.setOrderDate(new Date());
////        orderReq.put("Amount", order.getAmount());
////        orderReq.put("Status", order.getOrderStatus());
////
////        this.client = new RazorpayClient(razorPayKey, razorPaySecret);
////        Payment razorPayOrders= client.payments.createJsonPayment(orderReq);
////        log.info("razorPay payment {}",razorPayOrders);
//////        order.setRazorPayOrderId(razorPayOrders("id"));
//////        order.setOrderStatus(razorPayOrders.get("status"));
////        order.setOrderDate(new Date());
////
////        orderRepo.save(order);
////        return order;
////    }
//
//
//}
//
//
