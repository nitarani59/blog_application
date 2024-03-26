package com.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.React;

public interface ReactRepository extends JpaRepository<React, Integer>{
     
}
