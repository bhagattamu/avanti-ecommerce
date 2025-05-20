/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.service;

import com.avanti.ecommerce.dto.AddProductRequest;
import com.avanti.ecommerce.dto.ProductDto;
import com.avanti.ecommerce.dto.UpdateProductRequest;
import com.avanti.ecommerce.exception.CategoryNotFoundException;
import com.avanti.ecommerce.exception.ProductNotFoundException;
import com.avanti.ecommerce.model.Category;
import com.avanti.ecommerce.model.Product;
import com.avanti.ecommerce.repository.CategoryRepository;
import com.avanti.ecommerce.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class ProductServiceImplementation implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;
    
    
    @Override
    public ProductDto addProduct(AddProductRequest addProductRequest) {
        Product newProduct = new Product();
        Category cat = categoryRepository.findById(addProductRequest.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + addProductRequest.getCategoryId()));
        newProduct.setCategory(cat);
        System.out.println("com.avanti.ecommerce.service.ProductServiceImplementation.addProduct()" + addProductRequest.getStockInHand());
        newProduct.setName(addProductRequest.getName());
        newProduct.setDescription(addProductRequest.getDescription());
        newProduct.setPrice(addProductRequest.getPrice());
        newProduct.setProductImage(addProductRequest.getProductImage());
        newProduct.setStockInHand(addProductRequest.getStockInHand());
        return toProductDto(productRepository.save(newProduct));  
    }
    
    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productList = products.stream().map(this::toProductDto).collect(Collectors.toList());
        return productList;
    }

    @Override
    public ProductDto toProductDto(Product product) {
        ProductDto productData = new ProductDto();
        productData.setId(product.getId());
        productData.setName(product.getName());
        productData.setDescription(product.getDescription());
        productData.setPrice(product.getPrice());
        productData.setProductImage(product.getProductImage());
        productData.setStockInHand(product.getStockInHand());
        productData.setCategory(categoryService.toCategoryDto(product.getCategory()));
        return productData;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return toProductDto(product);
    }

    @Override
    public ProductDto updateProduct(UpdateProductRequest updateProductRequest) {
        Long id = updateProductRequest.getId();
        Product productFromDB = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productFromDB.setName(updateProductRequest.getName());
        productFromDB.setDescription(updateProductRequest.getDescription());
        Category catFromDB = categoryRepository.findById(updateProductRequest.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        productFromDB.setCategory(catFromDB);
        productFromDB.setPrice(updateProductRequest.getPrice());
        productFromDB.setStockInHand(updateProductRequest.getStockInHand());
        productFromDB.setProductImage(updateProductRequest.getProductImage());
        Product updatedProduct = productRepository.save(productFromDB);
        return toProductDto(updatedProduct);                
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    
    
    
    
    
}
