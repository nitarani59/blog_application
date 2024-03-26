package com.springboot.blog.service;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.response.ApiResponse;

public interface CommentService {
    ApiResponse createComment(CommentDto commentDto, Integer userId, Integer postId);
    void deleteComment(Integer commentId);
}
