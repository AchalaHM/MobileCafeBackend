package com.cafe.cafeback.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mobileNo;

    private String address;

    private String gender;

    private String nic;

    @OneToOne
    @JoinColumn(name = "userId" , referencedColumnName = "id")
    private User user;
}
