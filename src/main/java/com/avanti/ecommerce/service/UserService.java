/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avanti.ecommerce.service;

import com.avanti.ecommerce.dto.RegisterRequest;
import com.avanti.ecommerce.dto.UpdateUserRequest;
import com.avanti.ecommerce.dto.UserDto;
import com.avanti.ecommerce.model.User;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface UserService {
    public UserDto addUser(RegisterRequest user);
    public List<UserDto> getUsers();
    public UserDto getUserById(Long id);
    public UserDto getUserByEmail(String email);
    public UserDto updateUser(Long id, UpdateUserRequest user);
    public void disableUser(Long id);
    public UserDto toUserDto(User user);

}
