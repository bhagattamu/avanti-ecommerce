/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.dto;

import com.avanti.ecommerce.enums.OrderStatus;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author ACER
 */
@Data
public class OrderDto {
    private Long id;
    private UserDto user;
    private List<OrderItemDto> orderItems;
    private Date orderDate;
    private OrderStatus orderStatus;
}



