package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.response.ApiResponse;

public interface PostService {
    ApiResponse createPost(PostDto postDto);
    PostDto getPost(String postId);
    List<PostDto> getAllCategories();
    void deletePost(String postId);
    PostDto updatePost(String postId, PostDto postDto);
}
