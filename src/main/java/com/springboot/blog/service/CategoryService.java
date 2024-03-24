package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.dto.CategoryDto;
import com.springboot.blog.response.ApiResponse;

public interface CategoryService {
    ApiResponse createCategory(CategoryDto categoryDto);
    CategoryDto getCategory(String categoryId);
    List<CategoryDto> getAllCategories();
    void deleteCategory(String categoryId);
    CategoryDto updateCategory(String categoryId, CategoryDto categoryDto);
}
