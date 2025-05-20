/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.dto;

import lombok.Data;

/**
 *
 * @author ACER
 */
@Data
public class ProductDto {
    private Long id;
    private CategoryDto category;
    private String name;
    private String description;
    private Double price;
    private String productImage;
    private String productImagePath;
    private int stockInHand;
    
    public String getProductImagePath() {
        if(id == null || productImage == null) {
            return "/images/product-default.jpg";
        }
        return "/product-images/" + this.id + "/" + this.productImage;
    }
}
