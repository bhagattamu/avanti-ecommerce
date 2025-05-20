/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.controller;

import com.avanti.ecommerce.dto.AddProductRequest;
import com.avanti.ecommerce.dto.CategoryDto;
import com.avanti.ecommerce.dto.DeleteProductDto;
import com.avanti.ecommerce.dto.ProductDto;
import com.avanti.ecommerce.dto.UpdateProductRequest;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        model.addAttribute("updateProductRequest", new UpdateProductRequest());
        model.addAttribute("products", productList);
        return "manage-product";
    }

    @GetMapping("/product/{id}")
    public String shopProduct(@PathVariable String id, HttpSession session, Model model) {
        model.addAttribute("title", "Avanti Store - Shop Product");
        try {
            Long productId = Long.valueOf(id);
            ProductDto product = this.productService.getProductById(productId);
            model.addAttribute("product", product);
            return "shop-product";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            System.out.println("Failed! Please provide correct product id. Message: " + e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/admin/product")
    public String addProduct(@ModelAttribute AddProductRequest addProductRequest, @RequestParam("product") MultipartFile productImage, Model model, HttpSession session) {
        if (!isAdmin((String) session.getAttribute("role"))) {
            model.addAttribute("message", "Forbiden! Admin login is requred");
            System.out.println("Forbiden! Admin login is required");
            return "redirect:/";
        }
        try {
            if (!productImage.isEmpty()) {
                String fileName = StringUtils.cleanPath(productImage.getOriginalFilename());
                addProductRequest.setProductImage(fileName);
                ProductDto savedProduct = this.productService.addProduct(addProductRequest);
                String uploadDir = "product-images/" + savedProduct.getId();
                FileUploadUtil.saveFile(uploadDir, fileName, productImage);
            } else {
                this.productService.addProduct(addProductRequest);
            }
            System.out.println("Successfully saved a product");
            model.addAttribute("message", "Successfully saved a product");
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            System.out.println("Failed to create a product. Message: " + e.getMessage());
        }
        return "redirect:/admin/product";

    }

    @PostMapping("/admin/product/update")
    public String updateProduct(@RequestParam("product") MultipartFile productImage, @ModelAttribute UpdateProductRequest updateProductRequest, Model model, HttpSession session) {
        if (!isAdmin((String) session.getAttribute("role"))) {
            model.addAttribute("message", "Forbiden! Admin login is requred");
            System.out.println("Forbiden! Admin login is required");
            return "redirect:/login";
        }
        System.out.println("com.avanti.ecommerce.controller.ProductController.updateProduct(), PUT MAPPING");
        try {
            if (!productImage.isEmpty()) {
                String fileName = StringUtils.cleanPath(productImage.getOriginalFilename());
                updateProductRequest.setProductImage(fileName);
                ProductDto savedProduct = this.productService.updateProduct(updateProductRequest);
                String uploadDir = "product-images/" + savedProduct.getId();
                FileUploadUtil.saveFile(uploadDir, fileName, productImage);
            } else {
                this.productService.updateProduct(updateProductRequest);
            }
            System.out.println("Successfully saved a product");
            model.addAttribute("message", "Successfully saved a product");

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            System.out.println("Failed to create a product. Message: " + e.getMessage());
        }
        return "redirect:/admin/product";

    }

    @DeleteMapping("/admin/product")
    public void deleteProduct(@RequestBody DeleteProductDto deleteProduct, Model model, HttpSession session) {
        if (!isAdmin((String) session.getAttribute("role"))) {
            model.addAttribute("message", "Forbiden! Admin login is requred");
            System.out.println("Forbiden! Admin login is required");
            return;
        }
        System.out.println("com.avanti.ecommerce.controller.ProductController.deleteProduct()");
        try {
            Long productId = deleteProduct.getId();
            this.productService.deleteProduct(productId);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            System.out.println("Failed! Delete fail. Message: " + e.getMessage());
        }
    }
}
