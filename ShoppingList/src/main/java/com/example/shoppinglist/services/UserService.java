package com.example.shoppinglist.services;


import com.example.shoppinglist.model.entity.User;
import com.example.shoppinglist.model.entity.dtos.RegisterUserDTO;
import com.example.shoppinglist.model.entity.dtos.UserLoginDTO;
import com.example.shoppinglist.repositories.UserRepository;
import com.example.shoppinglist.session.CurrentLoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentLoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, CurrentLoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public boolean create(RegisterUserDTO registerUserDTO){
        Optional<User> byUsername = userRepository.findByUsername(registerUserDTO.getUsername());
        if (byUsername.isPresent()){
            return false;
        }
        User user = modelMapper.map(registerUserDTO,User.class);
        userRepository.save(user);
        return true;
    }
    public boolean login(UserLoginDTO loginDTO){
        Optional<User> user = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        if (user.isEmpty()){
            return false;
        }
        this.loggedUser.login(user.get());
        return true;
    }
    public void logout(){
        this.loggedUser.logout();
    }
}
