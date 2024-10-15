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

     @OneToMany(mappedBy ="userRegister",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
     private List<SecurityQuestionKey> securityQuestionList;


}
