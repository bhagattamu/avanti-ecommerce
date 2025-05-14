/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.service;


import com.avanti.ecommerce.dto.LoginRequest;
import com.avanti.ecommerce.dto.RegisterRequest;
import com.avanti.ecommerce.dto.UserDto;
import com.avanti.ecommerce.exception.UserNotFoundException;
import com.avanti.ecommerce.model.User;
import com.avanti.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class AuthServiceImplementation implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDto register(RegisterRequest registerRequest) {
        return this.userService.addUser(registerRequest);
    }

    @Override
    public UserDto login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        boolean isMatch = this.passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (isMatch == true) {
            System.out.println("Successfull login!!");
        } else {
            System.out.println("Login failed");
        }
        return this.userService.toUserDto(user);
    }
}
