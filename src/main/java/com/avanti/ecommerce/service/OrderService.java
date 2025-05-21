/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avanti.ecommerce.service;

import com.avanti.ecommerce.dto.AddOrderRequest;
import com.avanti.ecommerce.dto.ChangeOrderStatusDto;
import com.avanti.ecommerce.dto.OrderDto;
import com.avanti.ecommerce.enums.OrderStatus;
import com.avanti.ecommerce.model.Order;
import java.util.List;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

/**
 *
 * @author ACER
 */
public interface OrderService {
    public OrderDto createOrder(Long userId, AddOrderRequest createOrderRequest);
    public List<OrderDto> getOrders();
    public List<OrderDto> getUserOrders(Long userId);
    public void deleteOrder(Long orderId);
    public void changeOrderStatus(ChangeOrderStatusDto changeOrderStatusDto);
    public OrderDto toOrderDto(Order order);
}
