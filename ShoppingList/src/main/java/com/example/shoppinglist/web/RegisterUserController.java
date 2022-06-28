package com.example.shoppinglist.web;

import com.example.shoppinglist.model.entity.dtos.RegisterUserDTO;
import com.example.shoppinglist.repositories.UserRepository;
import com.example.shoppinglist.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegisterUserController {

    private final UserService userService;

    @Autowired
    public RegisterUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @ModelAttribute("passwordsMustMatch")
    public boolean passwordMatch(){
        return true;
    }

    @ModelAttribute("alreadyExists")
    public boolean userExists(){
        return false;
    }


    @PostMapping("/register")
    public String registerUser(@Valid RegisterUserDTO registerUserDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassWord())){
            redirectAttributes.addFlashAttribute("registerUserDTO",registerUserDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerUserDTO",bindingResult);
            if (!registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassWord())){
                redirectAttributes.addFlashAttribute("registerUserDTO",registerUserDTO);
                redirectAttributes.addFlashAttribute("passwordsMustMatch",false);
            }
            return "redirect:/register";
        }
        if (!userService.create(registerUserDTO)){
            redirectAttributes.addFlashAttribute("registerUserDTO",registerUserDTO);
            redirectAttributes.addFlashAttribute("alreadyExists",true);
            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @ModelAttribute("registerUserDTO")
    public RegisterUserDTO initRegisterUser(){
        return new RegisterUserDTO();
    }
}
