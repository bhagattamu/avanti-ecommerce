/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avanti.ecommerce.service;

import com.avanti.ecommerce.dto.LoginRequest;
import com.avanti.ecommerce.dto.RegisterRequest;
import com.avanti.ecommerce.dto.UserDto;



/**
 *
 * @author ACER
 */
public interface AuthService {
    UserDto register(RegisterRequest registerRequest);
    UserDto login(LoginRequest loginRequest);
}
