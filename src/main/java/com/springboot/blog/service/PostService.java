package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.response.ApiResponse;

public interface PostService {
    ApiResponse createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto getPost(Integer postId);
    List<PostDto> getAllCategories();
    void deletePost(Integer postId);
    PostDto updatePost(Integer postId, PostDto postDto);
    List<PostDto> getPostsByUserId(Integer userId);
    List<PostDto> getPostByCategoryId(Integer categoryId);
    List<PostDto> searchInPost(String keyword);
}
