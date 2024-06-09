package com.cafe.cafeback.controller;

import com.cafe.cafeback.dto.Response;
import com.cafe.cafeback.dto.UserDTO;
import com.cafe.cafeback.dto.UserLoginDTO;
import com.cafe.cafeback.service.JwtService;
import com.cafe.cafeback.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }


    @PostMapping("/addUser")
    public ResponseEntity<Response<String>> addUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.addUser(userDTO));
    }
    @PostMapping("/login")
    public ResponseEntity<Response<String>> login(@RequestBody UserLoginDTO userLoginDTO) {
        return ResponseEntity.ok(userService.loginUser(userLoginDTO));
    }

    /*postman request type
        {
            "name":"Achala Punsara",
            "userRole":"Admin",
            "email":"achla.magallagoda@gmail.com",
            "password":"Achala@123"
        }
    * */
}
