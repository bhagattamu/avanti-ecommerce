/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author ACER
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String url = request.getRequestURI();
        // Check if session to check if user is logged in
        if (session != null) {
            var loggedInUser = session.getAttribute("loggedInUser");
            // If user is not logged in and want to route to protected site redirect to login
            if (loggedInUser == null && !(url.equals("/login") || url.equals("/loginUser") || url.equals("/register") || url.equals("/registerUser"))) {
                response.sendRedirect("/login");
                return false;
            } 
            // if user is logged in and want to access login and register page redirect to home page
            else if(loggedInUser != null && (url.equals("/login") || url.equals("/loginUser") || url.equals("/register") || url.equals("/registerUser"))) {
                response.sendRedirect("/");
                return false;
            }
            
            return true;
        } else {
            // if not logged in and want to access login and register page - fine
            if (url.equals("/login") || url.equals("/loginUser") || url.equals("/register") || url.equals("/registerUser")) {
                return true;
            } 
            // if not logged in and want to access protected page - redirect to login page
            else {
                response.sendRedirect("/login");
                return false;
            }
        }
        
    }
}
