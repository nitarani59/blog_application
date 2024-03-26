package com.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.blog.entity.React;

public interface ReactRepository extends JpaRepository<React, Integer> {
    @Query("SELECT count(*) FROM React r WHERE r.post.id = :postId")
    public int countReactionsByPostId(@Param("postId") Integer postId);
}
