/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.dto;

import java.util.List;
import lombok.Data;

/**
 *
 * @author ACER
 */
@Data
public class AddOrderRequest {
    private Long userId;
    private List<AddOrderItemRequest> orderItems;
}
