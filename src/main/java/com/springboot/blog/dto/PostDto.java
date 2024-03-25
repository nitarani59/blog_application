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
public class PostDto {
    private String postId;

    @NotBlank(message = "Invalid value for title.", groups = {CreateValidation.class})
    @NotNull(message = "Invalid value for title.", groups = CreateValidation.class)
    private String title;
    
    @NotBlank(message = "Invalid value for content.", groups = {CreateValidation.class})
    @NotNull(message = "Invalid value for content.", groups = CreateValidation.class)
    private String content;

    @NotBlank(message = "Invalid value for image.", groups = {CreateValidation.class})
    @NotNull(message = "Invalid value for image.", groups = CreateValidation.class)
    private String image;
    private UserDto user;
    private CategoryDto category;
}
