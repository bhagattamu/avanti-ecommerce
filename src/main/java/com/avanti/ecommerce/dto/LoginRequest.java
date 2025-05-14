/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author ACER
 */
@Data
public class LoginRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;
    
    @NotBlank(message = "Password is required")
    private String password;
}
