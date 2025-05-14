/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.controller;

import com.avanti.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ACER
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
//    @GetMapping

//    @PostMapping()
//    public ResponseEntity<ApiResponse<?>> createUser(@Valid @RequestBody RegisterRequest newUser, UriComponentsBuilder uriBuilder) {
//        try {
//            UserDto user = userService.addUser(newUser);
//            var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
//            return ResponseEntity.created(uri).body(new ApiResponse<>(user, "User created successfully", true));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new ApiResponse<>(null, "Failed to create user", false));
//        }
//
//    }
//
//    @GetMapping()
//    public ResponseEntity<ApiResponse<?>> getUsers() {
//        try {
//            List<UserDto> users = userService.getUsers();
//            return ResponseEntity.ok().body(new ApiResponse<>(users, "Successfully fetched users", true));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new ApiResponse<>(null, "Failed to fetch users", false));
//        }
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ApiResponse<?>> getUserById(@PathVariable String id) {
//
//        try {
//            Long userId = Long.valueOf(id);
//            UserDto user = userService.getUserById(userId);
//            return ResponseEntity.ok().body(new ApiResponse<>(user, "Successfully fetched user", true));
//        } catch (NumberFormatException e) {
//            return ResponseEntity.badRequest().body(new ApiResponse<>(null, "Please provide correct user id", false));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new ApiResponse<>(null, "Failed to fetch user", false));
//        }
//    }
//    
//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponse<?>> updateUser(@PathVariable String id, @Valid @RequestBody UpdateUserRequest updateUserRequest, UriComponentsBuilder uriBuilder) {
//        try {
//            Long userId = Long.valueOf(id);
//            UserDto user = userService.updateUser(userId, updateUserRequest);
//            var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
//            return ResponseEntity.created(uri).body(new ApiResponse<>(user, "User updated successfully", true));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new ApiResponse<>(null, "Failed to update user", false));
//        }
//
//    }
//    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse<?>> deleteUser(@PathVariable String id) {
//        try {
//            Long userId = Long.valueOf(id);
//            userService.disableUser(userId);           
//            return ResponseEntity.ok().body(new ApiResponse<>(null, "User deleted successfully", true));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new ApiResponse<>(null, "Failed to delete user", false));
//        }
//
//    }
}
