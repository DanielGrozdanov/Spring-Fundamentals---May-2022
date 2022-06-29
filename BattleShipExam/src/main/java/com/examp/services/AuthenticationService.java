package com.examp.services;

import com.examp.models.User;
import com.examp.models.dtos.LoginDTO;
import com.examp.models.dtos.UserRegistrationDTO;
import com.examp.repositories.UserRepository;
import com.examp.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public AuthenticationService(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public boolean register(UserRegistrationDTO userRegistrationDTO) {
        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            return false;
        }

        Optional<User> byEmail = this.userRepository.findByEmail(userRegistrationDTO.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }
        Optional<User> userName = this.userRepository.findByUsername(userRegistrationDTO.getUsername());
        if (userName.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setFullName(userRegistrationDTO.getFullName());
        user.setPassword(userRegistrationDTO.getPassword());

        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> user = this.userRepository
                .findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
        if (user.isEmpty()) {
            return false;
        }
        this.loggedUser.login(user.get());
        return true;
    }

    public void logout() {
        loggedUser.logout();
    }
}
