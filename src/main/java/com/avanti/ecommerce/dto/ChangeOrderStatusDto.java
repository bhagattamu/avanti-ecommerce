/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.dto;

import com.avanti.ecommerce.enums.OrderStatus;
import lombok.Data;

/**
 *
 * @author ACER
 */
@Data
public class ChangeOrderStatusDto {
    private Long id;
    private OrderStatus status;
}
