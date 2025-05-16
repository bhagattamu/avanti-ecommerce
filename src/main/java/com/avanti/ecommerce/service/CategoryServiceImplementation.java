/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.service;

import com.avanti.ecommerce.dto.AddCategoryRequest;
import com.avanti.ecommerce.dto.CategoryDto;
import com.avanti.ecommerce.dto.UpdateCategoryRequest;
import com.avanti.ecommerce.exception.CategoryNotFoundException;
import com.avanti.ecommerce.model.Category;
import com.avanti.ecommerce.repository.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class CategoryServiceImplementation implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(AddCategoryRequest addCategoryRequest) {
        Category newCategory = new Category();
        newCategory.setName(addCategoryRequest.getName());
        newCategory.setDescription(addCategoryRequest.getDescription());
        return toCategoryDto(categoryRepository.save(newCategory));        
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        return toCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryList = categories.stream().map(this::toCategoryDto).collect(Collectors.toList());
        return categoryList;
    }

    @Override
    public CategoryDto updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest) {
        Category catFromDB = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        catFromDB.setName(updateCategoryRequest.getName());
        catFromDB.setDescription(updateCategoryRequest.getDescription());
        Category updatedCategory = categoryRepository.save(catFromDB);
        CategoryDto cat = toCategoryDto(updatedCategory);
        return cat;
    }
    
    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    
    @Override
    public CategoryDto toCategoryDto(Category cat) {
        CategoryDto category = new CategoryDto();
        category.setId(cat.getId());
        category.setName(cat.getName());
        category.setDescription(cat.getDescription());
        return category;
    }
}
