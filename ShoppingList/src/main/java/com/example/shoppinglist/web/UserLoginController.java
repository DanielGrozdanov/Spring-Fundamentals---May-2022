package com.example.shoppinglist.web;


import com.example.shoppinglist.model.entity.dtos.UserLoginDTO;
import com.example.shoppinglist.services.UserService;
import com.example.shoppinglist.session.CurrentLoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserLoginController {

    private UserService userService;
    private CurrentLoggedUser loggedUser;

    @Autowired
    public UserLoginController(UserService userService, CurrentLoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        loggedUser.logout();
        return "redirect:/";
    }


    @ModelAttribute("badCredentials")
    public boolean validCredentials(){
        return false;
    }


    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginDTO",userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO",bindingResult);
            return "redirect:/login";
        }
        if(!this.userService.login(userLoginDTO)){
            redirectAttributes.addFlashAttribute("userLoginDTO",userLoginDTO);
            redirectAttributes.addFlashAttribute("badCredentials",true);
            return "redirect:/login";
        }
        return "redirect:/home";
    }
    @ModelAttribute("userLoginDTO")
    public UserLoginDTO initUserLogin(){
        return new UserLoginDTO();
    }
}
