/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.controller;

import com.avanti.ecommerce.dto.AddProductRequest;
import com.avanti.ecommerce.dto.CategoryDto;
import com.avanti.ecommerce.dto.ProductDto;
import com.avanti.ecommerce.enums.Role;
import com.avanti.ecommerce.service.CategoryService;
import com.avanti.ecommerce.service.ProductService;
import com.avanti.ecommerce.util.FileUploadUtil;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ACER
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    private boolean isAdmin(String role) {
        return role == Role.ADMIN.name();
    }

    @GetMapping("/admin/product")
    public String manageProduct(HttpSession session, Model model) {
        if (!isAdmin((String) session.getAttribute("role"))) {
            model.addAttribute("message", "Forbiden! Admin login is requred");
            System.out.println("Forbiden! Admin login is required");
            return "redirect:/";
        }
        model.addAttribute("title", "Avanti Store - Manage Product");
        List<CategoryDto> catList = this.categoryService.getCategories();
        // For select category inside form
        model.addAttribute("categories", catList);
        List<ProductDto> productList = this.productService.getProducts();
        model.addAttribute("addProductRequest", new AddProductRequest());
        model.addAttribute("products", productList);
        return "manage-product";
    }

    @PostMapping("/admin/product")
    public String addProduct(@ModelAttribute AddProductRequest addProductRequest, @RequestParam("product") MultipartFile productImage, Model model, HttpSession session) {
        if (!isAdmin((String) session.getAttribute("role"))) {
            model.addAttribute("message", "Forbiden! Admin login is requred");
            System.out.println("Forbiden! Admin login is required");
            return "redirect:/";
        }
        try {
            if(!productImage.isEmpty()) {
                System.out.println("IN 1");
                String fileName = StringUtils.cleanPath(productImage.getOriginalFilename());
                System.out.println("IN 2");
                addProductRequest.setProductImage(fileName);
                System.out.println("IN 3");
                ProductDto savedProduct = this.productService.addProduct(addProductRequest);
                System.out.println("IN 4");
                String uploadDir = "product-images/" + savedProduct.getId();
                System.out.println("IN 5");
                FileUploadUtil.saveFile(uploadDir, fileName, productImage);
                System.out.println("IN 6");
            }
            System.out.println("Successfully saved a product");
            model.addAttribute("message", "Successfully saved a product");
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            System.out.println("Failed to create a product. Message: " + e.getMessage());
        }
        return "redirect:/admin/product";

    }

//    @PutMapping("/admin/category")
//    public String addCategory(@PathVariable String id, @ModelAttribute UpdateCategoryRequest updateCategoryRequest, Model model, HttpSession session) {
//        if (!isAdmin((Role) session.getAttribute("role"))) {
//            model.addAttribute("message", "Forbiden! Admin login is requred");
//            System.out.println("Forbiden! Admin login is required");
//            return "redirect:/";
//        }
//        try {
//            Long catId = Long.valueOf(id);
//            this.categoryService.updateCategory(catId, updateCategoryRequest);
//        } catch (Exception e) {
//            model.addAttribute("message", e.getMessage());
//            System.out.println("Failed! Please provide correct category id. Message: " + e.getMessage());
//        }
//        return "redirect:/admin/category";
//
//    }
//
//    @DeleteMapping("/admin/category")
//    public String deleteCategory(@PathVariable String id, Model model, HttpSession session) {
//        if (!isAdmin((Role) session.getAttribute("role"))) {
//            model.addAttribute("message", "Forbiden! Admin login is requred");
//            System.out.println("Forbiden! Admin login is required");
//            return "redirect:/";
//        }
//        try {
//            Long catId = Long.valueOf(id);
//            this.categoryService.deleteCategory(catId);
//        } catch (Exception e) {
//            model.addAttribute("message", e.getMessage());
//            System.out.println("Failed! Delete fail. Message: " + e.getMessage());
//        }
//        return "redirect:/admin/category";
//    }
}
