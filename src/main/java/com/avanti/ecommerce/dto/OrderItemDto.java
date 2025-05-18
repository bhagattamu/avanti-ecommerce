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
public class OrderItemDto {
    private Long id;
    private ProductDto product;
    private int quantity;
    private double price;
}
