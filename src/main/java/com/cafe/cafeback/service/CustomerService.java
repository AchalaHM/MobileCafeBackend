package com.cafe.cafeback.service;

import com.cafe.cafeback.dto.CustomerDTO;
import com.cafe.cafeback.dto.Response;
import com.cafe.cafeback.dto.UserDTO;

public interface CustomerService {

    Response<String> addCustomer(UserDTO userDTO , CustomerDTO customerDTO  );
}
