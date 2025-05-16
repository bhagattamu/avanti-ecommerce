/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce;

import com.avanti.ecommerce.enums.Role;
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
            // If user is not logged in and want to route to admin page redirect to login
            if (loggedInUser == null && !isAdmin((String) session.getAttribute("role")) && url.matches("/admin/*")) {
                response.sendRedirect("/login");
                return false;
            } // if user is logged in and want to access login and register page redirect to home page or not admin and want to access admin page redirect to home
            else if (loggedInUser != null && ((url.equals("/login") || url.equals("/register"))) || (!isAdmin((String) session.getAttribute("role")) && url.matches("/admin/*"))) {
                response.sendRedirect("/");
                return false;
            }

            return true;
        } else {
            // if not logged in and want to admin pages redirect to login
            if (url.matches("/admin/*")) {
                response.sendRedirect("/login");
                return false;
            } // if not logged in and want to access other pages - fine
            else {
                return true;
            }
        }

    }

    private boolean isAdmin(String role) {
        return role == Role.ADMIN.name();
    }
}
