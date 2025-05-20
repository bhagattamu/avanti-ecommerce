/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce;

import com.avanti.ecommerce.dto.AddProductRequest;
import com.avanti.ecommerce.dto.CategoryDto;
import com.avanti.ecommerce.dto.ProductDto;
import com.avanti.ecommerce.dto.UserDto;
import com.avanti.ecommerce.service.CategoryService;
import com.avanti.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ACER
 */
@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        model.addAttribute("title", "Avanti Store - Home");
        List<CategoryDto> catList = this.categoryService.getCategories();
        List<ProductDto> productList = this.productService.getProducts();
        model.addAttribute("categories", catList);
        model.addAttribute("products", productList);
        return "index";
    }
    
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        model.addAttribute("title", "Avanti Store - Home");
        
        List<CategoryDto> catList = this.categoryService.getCategories();
        List<ProductDto> productList = this.productService.getProducts();
        model.addAttribute("categories", catList);
        model.addAttribute("products", productList);
        return "index";
    }
    
    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        model.addAttribute("title", "Avanti Store - Cart");       
        return "cart";
    }
}
