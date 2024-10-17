package com.aus.corsafe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="userregistration")

public class UserRegister {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer userId;
     private String firstName;
     private String lastName;
     private String email;
     private Long phoneNumber;
     private String password;
     private String abn;
     private String companyName;
     private String companyAddress;
     private String state;
     private Long postalCode;

     @OneToMany(mappedBy ="userRegister",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
     private List<SecurityQuestionKey> securityQuestionList;


}
