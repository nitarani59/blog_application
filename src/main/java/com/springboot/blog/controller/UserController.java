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
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.dto.UserDto;
import com.springboot.blog.response.ApiResponse;
import com.springboot.blog.service.UserService;
import com.springboot.blog.validation.CreateValidation;
import com.springboot.blog.validation.UpdateValidation;

@RestController
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping("/user")
    ResponseEntity<ApiResponse> createUser(@RequestBody @Validated(CreateValidation.class) UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    ResponseEntity<UserDto> getUser(@PathVariable(name = "id") Integer userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    ResponseEntity<ApiResponse> deleteUser(@PathVariable(name = "id") Integer userId) {
        userService.deleteUser(userId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStausCode(HttpStatus.OK);;
        apiResponse.setMessage("User deleted successfully");
        apiResponse.setSuccess(true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") Integer userId, @RequestBody @Validated(UpdateValidation.class) UserDto userDto) {
        UserDto user = userService.updateUser(userId, userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
