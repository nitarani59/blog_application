package com.springboot.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.dto.CategoryDto;
import com.springboot.blog.response.ApiResponse;
import com.springboot.blog.service.CategoryService;
import com.springboot.blog.validation.CreateValidation;
import com.springboot.blog.validation.UpdateValidation;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    ResponseEntity<ApiResponse> createCategory(@RequestBody @Validated(CreateValidation.class) CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping()
    ResponseEntity<List<CategoryDto>> getAllCategorys() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<CategoryDto> getCategory(@PathVariable(name = "id") String categoryId) {
        return new ResponseEntity<>(categoryService.getCategory(categoryId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ApiResponse> deleteCategory(@PathVariable(name = "id") String categoryId) {
        categoryService.deleteCategory(categoryId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStausCode(HttpStatus.OK);;
        apiResponse.setMessage("Category deleted successfully");
        apiResponse.setSuccess(true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<CategoryDto> updateCategory(@PathVariable(name = "id") String categoryId, @RequestBody @Validated(UpdateValidation.class) CategoryDto categoryDto) {
        CategoryDto category = categoryService.updateCategory(categoryId, categoryDto);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
