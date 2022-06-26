package com.example.spotifyplaylistapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfigBean {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
