package com.springboot.blog.service.serviceImpementation;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.response.ApiResponse;
import com.springboot.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public ApiResponse createPost(PostDto postDto) {
       postRepository.save(modelMapper.map(postDto, Post.class));
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStausCode(HttpStatus.OK);
        apiResponse.setMessage("Post created");
        apiResponse.setSuccess(true);
        return apiResponse;
    }

    @Override
    public PostDto getPost(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
        new ResourceNotFoundException(String.format("Id %s does not exist.", postId)));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllCategories() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
        return postDtos;
    }

    @Override
    public void deletePost(String postId) {
        postRepository.findById(postId).orElseThrow(() ->
            new ResourceNotFoundException(String.format("Id %s does not exist.", postId)));
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto updatePost(String postId, PostDto postDto) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
        new ResourceNotFoundException(String.format("Id %s does not exist.", postId)));
        if (Objects.nonNull(postDto.getContent())) {
            post.setContent(postDto.getContent());
        }
        if (Objects.nonNull(postDto.getImage())) {
            post.setImage(postDto.getImage());
        }
        if (Objects.nonNull(postDto.getTitle())) {
            post.setTitle(postDto.getTitle());
        }
        return modelMapper.map(postRepository.save(post), PostDto.class);
    }
}
