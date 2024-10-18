package com.aus.corsafe.entity.auditRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private String abn;
    private String companyName;
    private String companyAddress;
    private String state;
    private Long postalCode;
    private long processInstanceKey;
    private long taskId;
    private Date createdAt;
    private Date updatedAt;
    private String assignee;
    private String implementation;

}
