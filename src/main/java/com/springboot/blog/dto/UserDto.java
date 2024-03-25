package com.springboot.blog.dto;

import com.springboot.blog.validation.CreateValidation;
import com.springboot.blog.validation.StringValidation;
import com.springboot.blog.validation.UpdateValidation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer userId;
    @StringValidation(message = "Invalid value for firstName.", groups = {CreateValidation.class, UpdateValidation.class})
    @NotNull(message = "firstName cannot be empty.", groups = CreateValidation.class)
    private String firstName;
    @StringValidation(message = "Invalid value for lastName.", groups = {CreateValidation.class, UpdateValidation.class})
    @NotNull(message = "lastName cannot be empty.", groups = CreateValidation.class)
    private String lastName;
    @Email(message = "Enter a valid email address.", groups = {CreateValidation.class, UpdateValidation.class})
    @NotNull(message = "Enter a valid email address.", groups = CreateValidation.class)
    private String email;
    @NotNull(message = "Invalid value for field password.", groups = CreateValidation.class)
    @Size(min = 8, message = "Password length must not less than 8 characters.", groups = {CreateValidation.class, UpdateValidation.class})
    private String password;
}
