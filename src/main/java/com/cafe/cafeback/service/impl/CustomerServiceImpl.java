package com.cafe.cafeback.service.impl;

import com.cafe.cafeback.dto.CustomerDTO;
import com.cafe.cafeback.dto.Response;
import com.cafe.cafeback.dto.UserDTO;
import com.cafe.cafeback.entity.Customer;
import com.cafe.cafeback.entity.User;
import com.cafe.cafeback.repository.CustomerRepository;
import com.cafe.cafeback.repository.UserRepository;
import com.cafe.cafeback.service.CustomerService;
//import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;


@Service
public class CustomerServiceImpl implements CustomerService {
//
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public CustomerServiceImpl(CustomerRepository customerRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

    @Override
    public Response<String> addCustomer( UserDTO userDTO , CustomerDTO customerDTO ) {

        try {

            if(userRepository.existsByEmail(userDTO.getEmail())){
                System.out.println("Email already exists");
                logger.warn(userDTO.getEmail() + " email a already exists in the system under another user");
                return new Response<>(1002, "Email already exists in the system under another user.", null);

            }

            User user = new User();
            user.setName(userDTO.getName());
            user.setUserRole(userDTO.getUserRole());
            user.setEmail(userDTO.getEmail());
//            String hashedPassword = this.passwordEncoder.encode(userDTO.getPassword());
            user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
            user.setAddedOn(LocalDate.now());
            user.setTerminatedOn(null);
            user = userRepository.save(user);
            Customer customer = new Customer();
            customer.setMobileNo(customerDTO.getMobileNo());
            customer.setAddress(customerDTO.getAddress());
            System.out.println("Address "+customerDTO.getAddress());
            customer.setGender(customerDTO.getGender());
            customer.setNic(customerDTO.getNic());
            customer.setUser(user);
            customerRepository.save(customer);

            logger.info("Customer added successfully");
            return new Response<>(1000 , "Customer added successfully" , "Customer added" + customer);

        }catch (Exception exception){
            return new Response<>(1001, "Error while adding customer" , null);
        }

    }

}



