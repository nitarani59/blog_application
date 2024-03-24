package com.springboot.blog.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// create a configuration class that will initialize ModelMapper
// and makes it available as a Bean in our application context.
@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper createMapper() {
        return new ModelMapper();
    }
}
