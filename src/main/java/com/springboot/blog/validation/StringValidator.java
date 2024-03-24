package com.springboot.blog.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StringValidator implements ConstraintValidator<StringValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext arg) {
        return value == null || value.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
    }
    
}
