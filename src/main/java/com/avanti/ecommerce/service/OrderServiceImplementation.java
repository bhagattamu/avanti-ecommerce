/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.service;

import com.avanti.ecommerce.dto.AddOrderItemRequest;
import com.avanti.ecommerce.dto.AddOrderRequest;
import com.avanti.ecommerce.dto.ChangeOrderStatusDto;
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
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class OrderServiceImplementation implements OrderService {

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
    @Transactional
    public OrderDto createOrder(Long userId, AddOrderRequest addOrderRequest) {
        Order order = new Order();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(new Date());
        Order savedOrder = orderRepository.save(order);
        for (AddOrderItemRequest addItemRequest : addOrderRequest.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            Product product = productRepository.findById(addItemRequest.getProductId()).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + addItemRequest.getProductId()));
            // Decrease the stock
            product.setStockInHand(product.getStockInHand() - addItemRequest.getQuantity());
            orderItem.setItem(product);
            orderItem.setPrice(addItemRequest.getPrice());
            orderItem.setQuantity(addItemRequest.getQuantity());
            // Update product after decreasing the stock count
            productRepository.save(product);
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
        double totalPrice = 0;
        List<OrderItemDto> orderItems = new ArrayList<>();
        for (OrderItem orderItemInDB : orderInDB.getOrderItems()) {
            OrderItemDto orderItem = new OrderItemDto();
            orderItem.setId(orderItemInDB.getId());
            orderItem.setProduct(productService.toProductDto(orderItemInDB.getItem()));
            orderItem.setPrice(orderItemInDB.getPrice());
            orderItem.setQuantity(orderItemInDB.getQuantity());
            orderItems.add(orderItem);
            totalPrice += orderItemInDB.getPrice();
        }
        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);
        return order;
    }

    @Override
    public List<OrderDto> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId, Sort.by(Sort.Direction.ASC, "orderDate"));
        List<OrderDto> orderList = orders.stream().map(this::toOrderDto).collect(Collectors.toList());
        return orderList;
    }

    @Override
    public List<OrderDto> getOrders() {
        List<Order> orders = orderRepository.findAll(Sort.by(Sort.Direction.ASC, "orderDate"));
        List<OrderDto> orderList = orders.stream().map(this::toOrderDto).collect(Collectors.toList());
        return orderList;
    }
    
    

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public void changeOrderStatus(ChangeOrderStatusDto changeOrderStatusDto) {
        Order orderFromDB = orderRepository.findById(changeOrderStatusDto.getId()).orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + changeOrderStatusDto.getId()));
        orderFromDB.setStatus(changeOrderStatusDto.getStatus());
        orderRepository.save(orderFromDB);
    }
}
