package com.cafe.cafeback.dto;

import com.cafe.cafeback.entity.User;
import lombok.Data;

@Data
public class CustomerDTO {
    private int id;
    private String mobileNo;
    private String address;
    private String gender;
    private String nic;
    private User user;
}
