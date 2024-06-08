package com.cafe.cafeback.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String userRole;
    private String email;
    private String password;
    private LocalDate addedOn;
    private LocalDate terminatedOn;

}
