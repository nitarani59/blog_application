package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.dto.UserDto;
import com.springboot.blog.response.ApiResponse;

public interface UserService {

    ApiResponse createUser(UserDto userDto);
    UserDto getUser(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
    UserDto updateUser(Integer userId, UserDto userDto);
}