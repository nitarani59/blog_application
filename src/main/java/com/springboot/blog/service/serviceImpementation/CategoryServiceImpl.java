package com.springboot.blog.service.serviceImpementation;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.dto.CategoryDto;
import com.springboot.blog.entity.Category;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.response.ApiResponse;
import com.springboot.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ApiResponse createCategory(CategoryDto categoryDto) {
       categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStausCode(HttpStatus.OK);
        apiResponse.setMessage("Category created");
        apiResponse.setSuccess(true);
        return apiResponse;
    }

    @Override
    public CategoryDto getCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
        new ResourceNotFoundException(String.format("Id %s does not exist.", categoryId)));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categorys = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categorys.stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();
        return categoryDtos;
    }

    @Override
    public void deleteCategory(String categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() ->
            new ResourceNotFoundException(String.format("Id %s does not exist.", categoryId)));
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
        new ResourceNotFoundException(String.format("Id %s does not exist.", categoryId)));
        if (Objects.nonNull(categoryDto.getTitle())) {
            category.setTitle(categoryDto.getTitle());
        }
        if (Objects.nonNull(categoryDto.getDescription())) {
            category.setDescription(categoryDto.getDescription());
        }
        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }
    
}
