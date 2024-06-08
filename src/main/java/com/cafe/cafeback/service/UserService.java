package com.cafe.cafeback.service;

import com.cafe.cafeback.dto.Response;
import com.cafe.cafeback.dto.UserDTO;
import com.cafe.cafeback.dto.UserLoginDTO;

public interface UserService {

    Response<String> addUser(UserDTO userDTO);

    Response<String> loginUser(UserLoginDTO userLoginDTO);
}
