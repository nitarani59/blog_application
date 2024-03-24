package com.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
