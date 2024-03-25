package com.springboot.blog.dto;

import com.springboot.blog.validation.CreateValidation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Integer categoryId;
    @NotNull(message = "Enter a title.", groups = CreateValidation.class)
    @NotBlank(message = "Enter a title.", groups = CreateValidation.class)
    private String title;
    private String description;
}
