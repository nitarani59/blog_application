package com.springboot.blog.service.serviceImpementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.dto.ReactDto;
import com.springboot.blog.entity.React;
import com.springboot.blog.entity.Post;
import com.springboot.blog.entity.User;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.ReactRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.response.ApiResponse;
import com.springboot.blog.service.ReactService;

@Service
public class ReactServiceImpl implements ReactService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReactRepository reactRepository;

    @Override
    public ApiResponse addReaction(ReactDto reactDto, Integer userId, Integer postId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
            new ResourceNotFoundException(String.format("UserId %s does not exist", userId)));
        Post post = postRepository.findById(postId).orElseThrow(() ->
            new ResourceNotFoundException(String.format("PostId %s does not exist", postId)));
        
        React react = modelMapper.map(reactDto, React.class);
        react.setUser(user);
        react.setPost(post);
        reactRepository.save(react);
        post.setReactionCount(reactRepository.findAll().size());
        postRepository.save(post);
        return new ApiResponse(HttpStatus.CREATED, "Reacted the post whose id is " + postId, true);
    }

    @Override
    public void removeReaction(Integer reactId) {
        reactRepository.deleteById(reactId);
    }
    
}
