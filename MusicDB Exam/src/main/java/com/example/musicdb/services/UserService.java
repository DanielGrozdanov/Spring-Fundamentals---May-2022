package com.example.musicdb.services;

import com.example.musicdb.models.entities.User;
import com.example.musicdb.models.entities.dtos.LoginUserDTO;
import com.example.musicdb.models.entities.dtos.RegisterUserDTO;
import com.example.musicdb.models.session.LoggedUser;
import com.example.musicdb.respositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private LoggedUser loggedUser;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public boolean register(RegisterUserDTO registerUserDTO){
        Optional<User> usernameExists = userRepository.findByUsername(registerUserDTO.getUsername());
        Optional<User> emailExists = userRepository.findByEmail(registerUserDTO.getEmail());
        if (usernameExists.isPresent()){
            return false;
        }
        if (emailExists.isPresent()){
            return false;
        }
        User user = modelMapper.map(registerUserDTO,User.class);
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


    public void logout(){
        this.loggedUser.logout();
    }
}
