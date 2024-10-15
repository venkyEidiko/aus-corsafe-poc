package com.aus.corsafe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class SecurityQuestionKey {
    @Id
    private int id;
    private String question;
    private String answer;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserRegister userRegister;
}
