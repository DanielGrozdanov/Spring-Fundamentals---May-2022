package com.example.spotifyplaylistapp.controller;


import com.example.spotifyplaylistapp.model.entity.dto.LoginUserDTO;
import com.example.spotifyplaylistapp.model.entity.dto.UserRegisterDTO;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthenticationController {
    private UserService userService;
    private LoggedUser loggedUser;

    @Autowired
    public AuthenticationController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("users/register")
    public String register() {
        return "register";
    }

    @GetMapping("users/login")
    public String login(){
        return "login";
    }

    @GetMapping("users/logout")
    public String logout(){
        if (loggedUser.getId() == null){
            return "redirect:/users/login";
        }
        loggedUser.logout();
        return "redirect:/";
    }

    @ModelAttribute("passwordsMustMatch")
    public boolean passwordMatch(){
        return true;
    }

    @ModelAttribute("userAlreadyExists")
    public boolean userExistsCheck(){
        return false;
    }

    @PostMapping("users/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            return "redirect:/users/register";
        }
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("passwordsMustMatch", false);
            return "redirect:/users/register";
        }
        if (!this.userService.create(userRegisterDTO)) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("userAlreadyExists", true);
            return "redirect:/users/register";
        }

    return "redirect:/users/login";
    }

    @ModelAttribute("userDoesNotExist")
    public boolean checkIfUserExist(){
        return true;
    }

    @PostMapping("users/login")
    public String login(@Valid LoginUserDTO loginUserDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginUserDTO", loginUserDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginUserDTO", bindingResult);
            return "redirect:/users/login";
        }
        if (!this.userService.login(loginUserDTO)){
            redirectAttributes.addFlashAttribute("loginUserDTO", loginUserDTO);
            redirectAttributes.addFlashAttribute("userDoesNotExist", false);
            return "redirect:/users/login";
        }

        return "redirect:/home";
    }


    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO initUserReg(){
        return new UserRegisterDTO();
    }
    @ModelAttribute("loginUserDTO")
    public LoginUserDTO initLogUser(){
        return new LoginUserDTO();
    }
}
