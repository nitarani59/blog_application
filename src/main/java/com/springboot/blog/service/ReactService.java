package com.springboot.blog.service;

import com.springboot.blog.dto.ReactDto;
import com.springboot.blog.response.ApiResponse;

public interface ReactService {
    ApiResponse addReaction(ReactDto reactDto, Integer userId, Integer postId);
    void removeReaction(Integer likeId);
}
