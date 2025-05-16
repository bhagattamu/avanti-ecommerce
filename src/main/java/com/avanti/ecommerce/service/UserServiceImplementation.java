/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.service;

import com.avanti.ecommerce.dto.RegisterRequest;
import com.avanti.ecommerce.dto.UpdateUserRequest;
import com.avanti.ecommerce.dto.UserDto;
import com.avanti.ecommerce.exception.UserNotFoundException;
import com.avanti.ecommerce.model.User;
import com.avanti.ecommerce.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDto addUser(RegisterRequest user) {
        User userData = new User();
        userData.setFirstName(user.getFirstName().trim());
        userData.setLastName(user.getLastName().trim());
        userData.setEmail(user.getEmail().trim());
        if (user.getPhoneNo() != null) {
            userData.setPhoneNo(user.getPhoneNo().trim());
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword().trim());
        userData.setPassword(hashedPassword);
        UserDto savedUser = toUserDto(userRepository.save(userData));
        return savedUser;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userList = users.stream().map(this::toUserDto).collect(Collectors.toList());
        return userList;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return toUserDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        return toUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UpdateUserRequest userData) {
        User userFromDB = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userFromDB.setFirstName(userData.getFirstName());
        userFromDB.setLastName(userData.getLastName());
        userFromDB.setEmail(userData.getEmail());
        userFromDB.setPhoneNo(userData.getPhoneNo());
        User updatedUser = userRepository.save(userFromDB);
        UserDto user = toUserDto(updatedUser);
        return user;
    }

    @Override
    public void disableUser(Long id) {
        User userFromDB = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userFromDB.setStatus(false);
        userRepository.save(userFromDB);
    }

    @Override
    public UserDto toUserDto(User user) {
        UserDto userData = UserDto.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getEmail()).phoneNo(user.getPhoneNo()).role(user.getRole()).status(user.isStatus()).build();
        return userData;
    }
}
