package com.springboot.blog.exception;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
