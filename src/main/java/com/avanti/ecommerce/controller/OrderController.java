/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.controller;

import com.avanti.ecommerce.dto.AddCategoryRequest;
import com.avanti.ecommerce.dto.AddOrderRequest;
import com.avanti.ecommerce.dto.ChangeOrderStatusDto;
import com.avanti.ecommerce.dto.DeleteOrderDto;
import com.avanti.ecommerce.dto.OrderDto;
import com.avanti.ecommerce.dto.UserDto;
import com.avanti.ecommerce.enums.OrderStatus;
import com.avanti.ecommerce.enums.Role;
import com.avanti.ecommerce.service.OrderService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author ACER
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    private boolean isAdmin(String role) {
        return role == Role.ADMIN.name();
    }

    @PostMapping("/order")
    public String createOrder(@ModelAttribute AddOrderRequest addOrderRequest, Model model, HttpSession session) {
        var user = (UserDto) session.getAttribute("loggedInUser");
        if (user == null) {
            model.addAttribute("message", "Please login to continue");
            return "redirect:/";
        } else {
            this.orderService.createOrder(user.getId(), addOrderRequest);
            model.addAttribute("message", "Successfully created an order");
            return "redirect:/orders";
        }

    }

    @GetMapping("/orders")
    public String getUserOrders(Model model, HttpSession session) {
        var user = (UserDto) session.getAttribute("loggedInUser");
        if (user == null) {
            model.addAttribute("message", "Please login to continue");
            return "redirect:/";
        } else {
            model.addAttribute("title", "Avanti Store - My Orders");
            List<OrderDto> orderList = orderService.getUserOrders(user.getId());
            model.addAttribute("orderList", orderList);
            model.addAttribute("message", "Successfully fetched users order");
        }
        return "order";
    }

    @GetMapping("/admin/order")
    public String getOrders(Model model, HttpSession session) {
        if (!isAdmin((String) session.getAttribute("role"))) {
            model.addAttribute("message", "Forbiden! Admin login is requred");
            System.out.println("Forbiden! Admin login is required");
            return "redirect:/";
        }
        model.addAttribute("title", "Avanti Store - Manage Orders");
        List<OrderDto> orderList = orderService.getOrders();
        model.addAttribute("orderList", orderList);
        model.addAttribute("message", "Successfully fetched orders");
        return "manage-order";
    }
    
    @DeleteMapping("/admin/order")
    public void deleteProduct(@RequestBody DeleteOrderDto deleteOrder, Model model, HttpSession session) {
        if (!isAdmin((String) session.getAttribute("role"))) {
            model.addAttribute("message", "Forbiden! Admin login is requred");
            System.out.println("Forbiden! Admin login is required");
            return;
        }
        try {
            Long orderId = deleteOrder.getId();
            this.orderService.deleteOrder(orderId);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            System.out.println("Failed! Delete fail. Message: " + e.getMessage());
        }
    }
    
    @PutMapping("/admin/order/status")
    public void changeOrderStatus(@RequestBody ChangeOrderStatusDto changeOrderStatusDto, Model model, HttpSession session) {
        if (!isAdmin((String) session.getAttribute("role"))) {
            model.addAttribute("message", "Forbiden! Admin login is requred");
            System.out.println("Forbiden! Admin login is required");
            return;
        }
        try {
            this.orderService.changeOrderStatus(changeOrderStatusDto);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            System.out.println("Failed! Failed to change order status. Message: " + e.getMessage());
        }
    }
}
