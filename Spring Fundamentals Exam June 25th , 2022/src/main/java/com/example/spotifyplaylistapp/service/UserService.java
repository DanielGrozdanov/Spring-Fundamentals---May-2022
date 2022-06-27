package com.example.spotifyplaylistapp.service;


import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.model.entity.dto.LoginUserDTO;
import com.example.spotifyplaylistapp.model.entity.dto.UserRegisterDTO;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private LoggedUser loggedUser;


    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public boolean create(UserRegisterDTO userRegisterDTO){
        Optional<User> byUsername = userRepository.findByUsername(userRegisterDTO.getUsername());
        Optional<User> byEmail = userRepository.findByEmail(userRegisterDTO.getEmail());

        if (byUsername.isPresent()){
            return false;
        }

        if (byEmail.isPresent()){
            return false;
        }

        User user = modelMapper.map(userRegisterDTO,User.class);
        userRepository.save(user);
        return true;
    }

    public boolean login(LoginUserDTO loginUserDTO){
        Optional<User> user = this.userRepository.findByUsername(loginUserDTO.getUsername());
        if (user.isPresent()){
            this.loggedUser.login(user.get());
        }else {
            return false;
        }
        return true;
    }

}

