package com.examp.controllers;


import com.examp.models.dtos.LoginDTO;
import com.examp.models.dtos.UserRegistrationDTO;
import com.examp.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @ModelAttribute("registrationDTO")
    public UserRegistrationDTO initRegistrationDTO(){
        return new UserRegistrationDTO();
    }

    @ModelAttribute("loginDTO")
    public LoginDTO initLoginDTO(){
        return new LoginDTO();
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO registrationDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !this.authenticationService.register(registrationDTO)){
            redirectAttributes.addFlashAttribute("registrationDTO",registrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO",bindingResult);

            return "redirect:/register";
        }

        return "redirect:/login";
    }


    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("loginDTO",loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO",bindingResult);

            return "redirect:/login";
        }

       if(!this.authenticationService.login(loginDTO)){
           redirectAttributes.addFlashAttribute("loginDTO",loginDTO);
           redirectAttributes.addFlashAttribute("badCredentials",true);
           return "redirect:/login";
       }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(){
        authenticationService.logout();
        return "redirect:/";
    }
}
