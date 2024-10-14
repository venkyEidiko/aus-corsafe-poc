package com.aus.corsafe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor

@Data
public class UserRegister {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer userId;
     private String firstName;
     private String lastName;
     private String email;
     private Long phoneNumber;
     private String password;

     public UserRegister() {
     }
}
