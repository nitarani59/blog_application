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

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.response.ApiResponse;
import com.springboot.blog.service.CommentService;
import com.springboot.blog.validation.CreateValidation;

@RestController
@RequestMapping("/api")
public class CommentController {
    
    @Autowired
    CommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}/comment")
    ResponseEntity<ApiResponse> createCategory(@RequestBody @Validated(CreateValidation.class) CommentDto commentDto, 
    @PathVariable Integer userId, @PathVariable Integer postId) {
        return new ResponseEntity<>(commentService.createComment(commentDto, userId, postId), HttpStatus.CREATED);
    }


    @DeleteMapping("/comment/{id}")
    ResponseEntity<ApiResponse> deleteComment(@PathVariable(name = "id") Integer commentId) {
        ApiResponse apiResponse = new ApiResponse();
        commentService.deleteComment(commentId);
        apiResponse.setStausCode(HttpStatus.OK);;
        apiResponse.setMessage("Category deleted successfully");
        apiResponse.setSuccess(true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
