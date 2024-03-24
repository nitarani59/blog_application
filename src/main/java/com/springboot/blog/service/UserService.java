package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.dto.UserDto;
import com.springboot.blog.response.ApiResponse;

public interface UserService {

    ApiResponse createUser(UserDto userDto);
    UserDto getUser(String userId);
    List<UserDto> getAllUsers();
    void deleteUser(String userId);
    UserDto updateUser(String userId, UserDto userDto);
}