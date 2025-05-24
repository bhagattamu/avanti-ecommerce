/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.controller;

import com.avanti.ecommerce.dto.LoginRequest;
import com.avanti.ecommerce.dto.RegisterRequest;
import com.avanti.ecommerce.dto.UserDto;
import com.avanti.ecommerce.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ACER
 */
@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("title", "Avanti Store - Register");
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute RegisterRequest registerRequest, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            UserDto user = this.authService.register(registerRequest);
            model.addAttribute("message", "Successfully registered a user and logged in");
            session.setAttribute("loggedInUser", user);
            session.setAttribute("role", user.getRole().name());
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            System.out.println("Failed to register user. Message: " + e.getMessage());
            return "redirect:/register";       
        }
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("title", "Avanti Store - Login");
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";

    }

    @PostMapping("/loginUser")
    public String loginUser(@ModelAttribute LoginRequest loginRequest, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try{
            UserDto user = this.authService.login(loginRequest);
        model.addAttribute("message", "Successfully logged in a user");
        session.setAttribute("loggedInUser", user);
        session.setAttribute("role", user.getRole().name());
        return "redirect:/";
        }catch(Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            System.out.println("Failed to login user. Message: " + e.getMessage());
            return "redirect:/login";   
        }
        
    }

    @PostMapping("/logout")
    public String logoutUser(Model model, HttpSession session) {
        System.out.println("com.avanti.ecommerce.controller.AuthController.logoutUser()");
        model.addAttribute("message", "Successfully logged out a user");
        session.invalidate();
        return "redirect:/login";
    }

}
