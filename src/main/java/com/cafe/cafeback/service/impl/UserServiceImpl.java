package com.cafe.cafeback.service.impl;

import com.cafe.cafeback.dto.Response;
import com.cafe.cafeback.dto.UserDTO;
import com.cafe.cafeback.dto.UserLoginDTO;
import com.cafe.cafeback.entity.User;
import com.cafe.cafeback.repository.UserRepository;
import com.cafe.cafeback.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtServiceImpl jwtService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtServiceImpl jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public Response<String> addUser(UserDTO userDTO) {
        try {
            if(userRepository.existsByEmail(userDTO.getEmail())){
                System.out.println("Email already exists");
                logger.error("Already exist email");
                return new Response<>(1002, "Existing email", null);
            }

            User user = new User();
            user.setName(userDTO.getName());
            user.setUserRole(userDTO.getUserRole());
            user.setEmail(userDTO.getEmail());
//            String hashedPassword = this.passwordEncoder.encode(userDTO.getPassword());
            user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
            user.setAddedOn(LocalDate.now());
            user.setTerminatedOn(null);

            userRepository.save(user);

            logger.info("User create successfully");
            System.out.println("User created successfully");
            return new Response<>(1000 , "User Created Successfully" , "User Created :" + user);

        }catch (Exception e){
            logger.error("Issue in creating user");
            System.out.println("Issue in creating user");
            return new Response<>(1001 , "issue in creating user", null);
        }
    }
    @Override
    public Response<String> loginUser(UserLoginDTO userLoginDTO) {
        User user = userRepository.findByEmail(userLoginDTO.getEmail());
        if (user != null && passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            String token = jwtService.generateToken(user.getEmail(), user.getUserRole());
            logger.info("User logged in successfully as "+ user.getUserRole());
            return new Response<>(1000, "User logged in as "+ user.getUserRole(), token);
        }
        logger.warn("Invalid Email or password");
        return new Response<>(1001, "Invalid email or password", null);
    }
//    private String hashPassword(String password) throws NoSuchAlgorithmException {
//        // Create SHA-256 hash
//        MessageDigest digest = MessageDigest.getInstance("SHA-256");
//        byte[] hash = digest.digest(password.getBytes());
//
//        // Convert byte array to Base64 string
//        return Base64.getEncoder().encodeToString(hash);
//    }
}
