package com.aus.corsafe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Products {
    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)  // Use IDENTITY for Oracle
    private Integer productId;

    private String name;
    private String description;

    private Double price;  // Use Double to accommodate precision
    private Integer stockQuantity;

    @Lob
    @Column(name = "productImage")  // No need for columnDefinition
    private byte[] productImage;  // BLOB is implied for byte[] with @Lob
}
