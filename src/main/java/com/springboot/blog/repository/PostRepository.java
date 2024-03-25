package com.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.entity.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String>{
    public List<Post> findByUser(User user);
    public List<Post> findByCategory(Category category);
    public List<Post> findByTitleContaining(String title);
}
