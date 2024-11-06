package com.aus.corsafe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ORDER_TABLE")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String area;
    private String city;
    private String postalCode;
    private String orderStatus;  // "Pending" initially, "Success" after payment
    private Double amount;
    private Date orderDate;

    private String razorPayOrderId;
    private Integer userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<CartItem> cartItems = new ArrayList<>();



}
