/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author ACER
 */
@Data
public class UpdateProductRequest {
    @NotEmpty(message = "Product id should not be empty")
    private Long id;
    
    @NotEmpty(message = "Category id should not be empty")
    private Long categoryId;
    
    @NotEmpty(message = "Name can not be empty")
    private String name;
    
    @NotEmpty(message = "Description can not be empty")
    private String description;
    
    @NotEmpty(message = "Price should not be empty")
    @Min(value = 0L, message = "Price should be positive")
    private Double price;
    
    @NotEmpty(message = "Stock in hand should not be empty")
    @Min(value = 1, message = "Stock in hand should not be 0 when adding a product")
    private Integer stockInHand;
    
    private String productImage;
    
    
}
