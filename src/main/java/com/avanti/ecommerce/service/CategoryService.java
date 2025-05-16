/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avanti.ecommerce.service;

import com.avanti.ecommerce.dto.AddCategoryRequest;
import com.avanti.ecommerce.dto.CategoryDto;
import com.avanti.ecommerce.dto.UpdateCategoryRequest;
import com.avanti.ecommerce.model.Category;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface CategoryService {
    public CategoryDto addCategory(AddCategoryRequest addCategoryRequest);
    public CategoryDto getCategoryById(Long id);
    public List<CategoryDto> getCategories();
    public CategoryDto updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest);
    public CategoryDto toCategoryDto(Category category);
    public void deleteCategory(Long id);
}
