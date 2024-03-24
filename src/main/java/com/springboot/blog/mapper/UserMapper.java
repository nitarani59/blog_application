package com.springboot.blog.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.blog.dto.UserDto;
import com.springboot.blog.entity.User;

public class UserMapper {
    
    @Autowired
    ModelMapper modelMapper;

    public UserDto maptoUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User mapToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
