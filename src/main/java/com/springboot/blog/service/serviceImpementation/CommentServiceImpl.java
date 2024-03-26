package com.springboot.blog.service.serviceImpementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.entity.User;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.response.ApiResponse;
import com.springboot.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ApiResponse createComment(CommentDto commentDto, Integer userId, Integer postId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
            new ResourceNotFoundException(String.format("UserId %s does not exist", userId)));
        Post post = postRepository.findById(postId).orElseThrow(() ->
            new ResourceNotFoundException(String.format("PostId %s does not exist", postId)));
        
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        commentRepository.save(comment);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Comment added successfully");
        apiResponse.setStausCode(HttpStatus.OK);
        apiResponse.setSuccess(true);
        return apiResponse;
    }

    @Override
    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
    }
    
}
