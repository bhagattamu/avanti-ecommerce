/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.controller;

import com.avanti.ecommerce.dto.AddCategoryRequest;
import com.avanti.ecommerce.dto.AddOrderRequest;
import com.avanti.ecommerce.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ACER
 */
@Controller
public class OrderController {
    
    private OrderService orderService;
    
    @PostMapping("/order")
    public String createOrder(@ModelAttribute AddOrderRequest addOrderRequest, Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            model.addAttribute("message", "Please login to continue");
            return "redirect:/";
        }
        this.orderService.createOrder(addOrderRequest);
        model.addAttribute("message", "Successfully created an order");
        return "redirect:/";
    }
}
