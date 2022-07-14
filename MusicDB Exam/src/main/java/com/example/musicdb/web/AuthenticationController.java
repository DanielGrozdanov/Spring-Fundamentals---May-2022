package com.example.musicdb.web;


import com.example.musicdb.models.entities.dtos.LoginUserDTO;
import com.example.musicdb.models.entities.dtos.RegisterUserDTO;
import com.example.musicdb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }

    @ModelAttribute("passwordsMustMatch")
    public boolean passwordMatch() {
        return true;
    }
    @ModelAttribute("alreadyExists")
    public boolean userAlreadyExists(){
        return false;
    }

    @PostMapping("/register")
    public String register(@Valid RegisterUserDTO registerUserDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerUserDTO", registerUserDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerUserDTO", bindingResult);
            return "redirect:/users/register";
        }

        if (!registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("registerUserDTO", registerUserDTO);
            redirectAttributes.addFlashAttribute("passwordsMustMatch", false);
            return "redirect:/users/register";
        }
        if (!this.userService.register(registerUserDTO)) {
            redirectAttributes.addFlashAttribute("registerUserDTO", registerUserDTO);
            redirectAttributes.addFlashAttribute("alreadyExists", true);
            return "redirect:/users/register";
        }
        return "redirect:/users/login";
    }

    @ModelAttribute("userDoesNotExist")
    public boolean userExistsCheck(){
        return true;
    }
    @PostMapping("/login")
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


    @ModelAttribute("loginUserDTO")
    public LoginUserDTO initLoginDTO(){
        return new LoginUserDTO();
    }
    @ModelAttribute("registerUserDTO")
    public RegisterUserDTO initRegisterUser() {
        return new RegisterUserDTO();
    }
}
