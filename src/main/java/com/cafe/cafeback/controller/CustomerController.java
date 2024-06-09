package com.cafe.cafeback.controller;

import com.cafe.cafeback.dto.CustomerWrapper;
import com.cafe.cafeback.dto.Response;
import com.cafe.cafeback.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping()
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<Response<String>> addCustomer(@RequestBody CustomerWrapper customerWrapper){
        return ResponseEntity.ok(customerService.addCustomer( customerWrapper.getUserDTO() , customerWrapper.getCustomerDTO() ));
    }

    /*Postman request type for add customer
    *
    *{
    "userDTO": {
        "name": "Steven Smith",
        "userRole": "customer",
        "email": "stevesmith49@gmail.com",
        "password": "pass#123"
    },
    "customerDTO": {
        "nic": "892892Z",
        "mobileNo": "0710000111",
        "gender": "Male",
        "address": "Sydney"
    }
}*/

}
