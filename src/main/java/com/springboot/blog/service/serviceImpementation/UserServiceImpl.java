package com.springboot.blog.service.serviceImpementation;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.dto.UserDto;
import com.springboot.blog.entity.User;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.response.ApiResponse;
import com.springboot.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;
    
    @Override
    public ApiResponse createUser(UserDto userDto) {
        User user = userRepository.save(modelMapper.map(userDto, User.class));
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStausCode(HttpStatus.OK);
        apiResponse.setMessage("User created");
        apiResponse.setSuccess(true);
        return apiResponse;
    }

    @Override
    public UserDto getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
        new ResourceNotFoundException(String.format("Id %s does not exist.", userId)));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
        return userDtos;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(String.format("Id %s does not exist.", userId)));
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(() ->
        new ResourceNotFoundException(String.format("Id %s does not exist.", userId)));
        if (Objects.nonNull(userDto.getFirstName())) {
            user.setFirstName(userDto.getFirstName());
        }
        if (Objects.nonNull(userDto.getLastName())) {
            user.setLastName(userDto.getLastName());
        }
        if (Objects.nonNull(userDto.getEmail())) {
            user.setEmail(userDto.getEmail());
        }
        if (Objects.nonNull(userDto.getPassword())) {
            user.setPassword(userDto.getPassword());
        }
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }
}
