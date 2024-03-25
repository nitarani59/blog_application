package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.response.ApiResponse;

public interface PostService {
    ApiResponse createPost(PostDto postDto, String userId, String categoryId);
    PostDto getPost(String postId);
    List<PostDto> getAllCategories();
    void deletePost(String postId);
    PostDto updatePost(String postId, PostDto postDto);
    List<PostDto> getPostsByUserId(String userId);
    List<PostDto> getPostByCategoryId(String categoryId);
    List<PostDto> searchInPost(String keyword);
}
