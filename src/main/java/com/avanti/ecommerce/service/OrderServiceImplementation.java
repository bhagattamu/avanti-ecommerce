/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.service;

import com.avanti.ecommerce.dto.AddOrderItemRequest;
import com.avanti.ecommerce.dto.AddOrderRequest;
import com.avanti.ecommerce.dto.OrderDto;
import com.avanti.ecommerce.dto.OrderItemDto;
import com.avanti.ecommerce.enums.OrderStatus;
import com.avanti.ecommerce.exception.OrderNotFoundException;
import com.avanti.ecommerce.exception.ProductNotFoundException;
import com.avanti.ecommerce.exception.UserNotFoundException;
import com.avanti.ecommerce.model.Order;
import com.avanti.ecommerce.model.OrderItem;
import com.avanti.ecommerce.model.Product;
import com.avanti.ecommerce.model.User;
import com.avanti.ecommerce.repository.OrderItemRepository;
import com.avanti.ecommerce.repository.OrderRepository;
import com.avanti.ecommerce.repository.ProductRepository;
import com.avanti.ecommerce.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class OrderServiceImplementation implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductService productService;
    
    @Override
    public OrderDto createOrder(AddOrderRequest addOrderRequest) {
        Order order = new Order();
        User user = userRepository.findById(addOrderRequest.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found with id: " + addOrderRequest.getUserId()));
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(new Date());
        Order savedOrder = orderRepository.save(order);
        for(AddOrderItemRequest addItemRequest: addOrderRequest.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            Product product = productRepository.findById(addItemRequest.getProductId()).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + addItemRequest.getProductId()));
            orderItem.setItem(product);
            orderItem.setPrice(addItemRequest.getPrice());
            orderItem.setQuantity(addItemRequest.getQuantity());
            orderItemRepository.save(orderItem);
        }
        Order orderInDB = orderRepository.findById(savedOrder.getId()).orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + savedOrder.getId()));
        return toOrderDto(orderInDB);
    }
    
    @Override
    public OrderDto toOrderDto(Order orderInDB) {
        OrderDto order = new OrderDto();
        order.setId(orderInDB.getId());
        
        order.setUser(userService.toUserDto(orderInDB.getUser()));
        order.setOrderDate(orderInDB.getOrderDate());
        order.setOrderStatus(orderInDB.getStatus());
        List<OrderItemDto> orderItems = new ArrayList<>();
        for(OrderItem orderItemInDB: orderInDB.getOrderItems()) {
            OrderItemDto orderItem = new OrderItemDto();
            orderItem.setId(orderItemInDB.getId());
            orderItem.setProduct(productService.toProductDto(orderItemInDB.getItem()));
            orderItem.setPrice(orderItemInDB.getPrice());
            orderItem.setQuantity(orderItemInDB.getQuantity());
            orderItems.add(orderItem);            
        }
        order.setOrderItems(orderItems);
        return order;
    }
}
