/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce;

import com.avanti.ecommerce.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ACER
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        model.addAttribute("title", "Avanti Store - Home");
        return "index";
    }
    
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        model.addAttribute("title", "Avanti Store - Home");
        return "index";
    }
}
