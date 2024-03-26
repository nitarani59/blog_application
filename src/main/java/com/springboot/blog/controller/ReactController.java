package com.springboot.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.dto.ReactDto;
import com.springboot.blog.response.ApiResponse;
import com.springboot.blog.service.ReactService;
import com.springboot.blog.validation.CreateValidation;

@RestController
@RequestMapping("/api")
public class ReactController {
    
    @Autowired
    ReactService reactService;

    @PostMapping("/user/{userId}/post/{postId}/react")
    ResponseEntity<ApiResponse> createCategory(@RequestBody @Validated(CreateValidation.class) ReactDto reactDto, 
    @PathVariable Integer userId, @PathVariable Integer postId) {
        return new ResponseEntity<>(reactService.addReaction(reactDto, userId, postId), HttpStatus.CREATED);
    }


    @DeleteMapping("/react/{id}")
    ResponseEntity<ApiResponse> deleteComment(@PathVariable(name = "id") Integer commentId) {
        ApiResponse apiResponse = new ApiResponse();
        reactService.removeReaction(commentId);
        apiResponse.setStausCode(HttpStatus.OK);;
        apiResponse.setMessage("Category deleted successfully");
        apiResponse.setSuccess(true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
