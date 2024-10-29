package com.aus.corsafe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Products product;

    private Integer quantity;

    private Integer userId;

    private Long price;

    private Long totalPrice;


    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Orders order;
}
