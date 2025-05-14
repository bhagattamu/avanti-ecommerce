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
public class UpdateUserRequest {
   @NotBlank(message = "First Name is requered")
    private String firstName;
    
    @NotBlank(message = "Last Name is required")
    private String lastName;
    
    @NotBlank(message = "Email is requeired")
    @Email(message= "Email is not valid")
    private String email;
    
    private String phoneNo;
}
