package com.example.coffeshop.services;

import com.example.coffeshop.model.dto.UserLoginDTO;
import com.example.coffeshop.model.dto.UserRegisterDTO;
import com.example.coffeshop.model.entity.User;
import com.example.coffeshop.model.views.UserViewOrderDTO;
import com.example.coffeshop.model.views.ViewOrderDTO;
import com.example.coffeshop.repositories.UserRepository;
import com.example.coffeshop.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public boolean registration(UserRegisterDTO userRegisterDTO){
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())){
            return false;
        }
        Optional<User> byEmail = this.userRepository.findByEmail(userRegisterDTO.getEmail());
        Optional<User> byUsername = this.userRepository.findByUsername(userRegisterDTO.getUsername());

        if (byEmail.isPresent()){
            return false;
        }
        if (byUsername.isPresent()){
            return false;
        }
        User user = modelMapper.map(userRegisterDTO,User.class);
        userRepository.save(user);
        return true;
    }

    public boolean login(UserLoginDTO userLoginDTO){
        Optional<User> user = this.userRepository
                .findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        if (user.isEmpty()){
           return false;
        }
        this.loggedUser.login(user.get());
        return true;
    }

    public void logout(){
        loggedUser.logout();
    }

    public List<UserViewOrderDTO> findAllUsersAndCountOfOrdersDesc() {
        return userRepository.findAllByOrderByOrdersDesc().stream()
                .map(user->{
            UserViewOrderDTO userViewOrderDTO = new UserViewOrderDTO();
            userViewOrderDTO.setUsername(user.getUsername())
                    .setCountOfOrders(user.getOrders().size());

            return userViewOrderDTO;
        }).collect(Collectors.toList());
    }
}
