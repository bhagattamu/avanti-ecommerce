/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avanti.ecommerce.service;

import com.avanti.ecommerce.dto.AddProductRequest;
import com.avanti.ecommerce.dto.ProductDto;
import com.avanti.ecommerce.model.Product;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface ProductService {

    public ProductDto addProduct(AddProductRequest addProductRequest);
    public List<ProductDto> getProducts();
    public ProductDto getProductById(Long id);    
    public ProductDto toProductDto(Product product);
}
