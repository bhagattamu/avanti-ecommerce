/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author ACER
 */
@Data
public class RegisterRequest {

    @NotBlank(message = "First Name is requered")
    @Size(min = 5, message = "First name should be at least 5 characters")

    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Size(min = 5, message = "Last name should be at least 5 characters")

    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    private String phoneNo;

    @NotBlank(message = "Password is required")
    private String password;
}
