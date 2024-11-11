package com.aus.corsafe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId", nullable = false)
    private Products product;


    private Integer quantity;

    private Double price;

    private Integer userId;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // Calculated price  based on quantity * price
    public Double calculateTotalPrice() {
        return quantity * price;
    }

}