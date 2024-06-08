package com.cafe.cafeback.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class UserDTO {

    private int id;
    private String name;
    private String userRole;
    private String email;
    private String password;
    private LocalDate addedOn;
    private LocalDate terminatedOn;
}
