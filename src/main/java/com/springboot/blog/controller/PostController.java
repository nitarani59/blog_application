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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.response.ApiResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.validation.CreateValidation;
import com.springboot.blog.validation.UpdateValidation;

@RestController
@RequestMapping("/api")
public class PostController {
    
    @Autowired
    PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    ResponseEntity<ApiResponse> createPost(@RequestBody @Validated(CreateValidation.class) PostDto postDto,
    @PathVariable Integer userId, @PathVariable Integer categoryId) {
        return new ResponseEntity<>(postService.createPost(postDto, userId, categoryId), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/post")
    ResponseEntity<List<PostDto>> getAllPostsByUserId(@PathVariable Integer userId) {
        return new ResponseEntity<>(postService.getPostsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/post")
    ResponseEntity<List<PostDto>> getAllPostsByCategoryId(@PathVariable Integer categoryId) {
        return new ResponseEntity<>(postService.getPostByCategoryId(categoryId), HttpStatus.OK);
    }

    @GetMapping("/post")
    ResponseEntity<List<PostDto>> getAllPosts() {
        return new ResponseEntity<>(postService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    ResponseEntity<PostDto> getPost(@PathVariable(name = "id") Integer postId) {
        return new ResponseEntity<>(postService.getPost(postId), HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    ResponseEntity<ApiResponse> deletePost(@PathVariable(name = "id") Integer postId) {
        postService.deletePost(postId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStausCode(HttpStatus.OK);;
        apiResponse.setMessage("Post deleted successfully");
        apiResponse.setSuccess(true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/post/{id}")
    ResponseEntity<PostDto> updatePost(@PathVariable(name = "id") Integer postId, @RequestBody @Validated(UpdateValidation.class) PostDto postDto) {
        PostDto post = postService.updatePost(postId, postDto);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/searchPost")
    ResponseEntity<List<PostDto>> serachKeyword(@RequestParam String search) {
        return new ResponseEntity<>(postService.searchInPost(search), HttpStatus.OK);
    }
}
