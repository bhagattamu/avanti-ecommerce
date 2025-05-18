/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avanti.ecommerce.service;

import com.avanti.ecommerce.dto.AddOrderRequest;
import com.avanti.ecommerce.dto.OrderDto;
import com.avanti.ecommerce.model.Order;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

/**
 *
 * @author ACER
 */
public interface OrderService {
    public OrderDto createOrder(AddOrderRequest createOrderRequest);
    public OrderDto toOrderDto(Order order);
}
