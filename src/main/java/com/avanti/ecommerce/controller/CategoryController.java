/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.controller;

import com.avanti.ecommerce.dto.AddCategoryRequest;
import com.avanti.ecommerce.dto.CategoryDto;
import com.avanti.ecommerce.dto.UpdateCategoryRequest;
import com.avanti.ecommerce.enums.Role;
import com.avanti.ecommerce.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author ACER
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private boolean isAdmin(String role) {
        return role == Role.ADMIN.name();
    }

    @GetMapping("/admin/category")
    public String manageCategory(HttpSession session, Model model) {
        if (!isAdmin((String) session.getAttribute("role"))) {
            model.addAttribute("message", "Forbiden! Admin login is requred");
            System.out.println("Forbiden! Admin login is required");
            return "redirect:/";
        }
        model.addAttribute("title", "Avanti Store - Manage Category");
        List<CategoryDto> catList = this.categoryService.getCategories();
        model.addAttribute("addCategoryRequest", new AddCategoryRequest());
        model.addAttribute("categories", catList);
        return "manage-category";
    }

    @PostMapping("/admin/category")
    public String addCategory(@ModelAttribute AddCategoryRequest addCategoryRequest, Model model, HttpSession session) {
        if (!isAdmin((String) session.getAttribute("role"))) {
            model.addAttribute("message", "Forbiden! Admin login is requred");
            System.out.println("Forbiden! Admin login is required");
            return "redirect:/";
        }
        this.categoryService.addCategory(addCategoryRequest);
        return "redirect:/admin/category";
    }

    @PutMapping("/admin/category")
    public String addCategory(@PathVariable String id, @ModelAttribute UpdateCategoryRequest updateCategoryRequest, Model model, HttpSession session) {
        if (!isAdmin((String) session.getAttribute("role"))) {
            model.addAttribute("message", "Forbiden! Admin login is requred");
            System.out.println("Forbiden! Admin login is required");
            return "redirect:/";
        }
        try {
            Long catId = Long.valueOf(id);
            this.categoryService.updateCategory(catId, updateCategoryRequest);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            System.out.println("Failed! Please provide correct category id. Message: " + e.getMessage());
        }
        return "redirect:/admin/category";

    }

    @DeleteMapping("/admin/category")
    public String deleteCategory(@PathVariable String id, Model model, HttpSession session) {
        if (!isAdmin((String) session.getAttribute("role"))) {
            model.addAttribute("message", "Forbiden! Admin login is requred");
            System.out.println("Forbiden! Admin login is required");
            return "redirect:/";
        }
        try {
            Long catId = Long.valueOf(id);
            this.categoryService.deleteCategory(catId);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            System.out.println("Failed! Delete fail. Message: " + e.getMessage());
        }
        return "redirect:/admin/category";
    }

}
