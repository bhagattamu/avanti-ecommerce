/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author ACER
 */
@Data
public class AddCategoryRequest {
    @NotEmpty(message = "Name can not be empty")
    private String name;
    
    @NotEmpty(message = "Description can not be empty")
    private String description;
}
