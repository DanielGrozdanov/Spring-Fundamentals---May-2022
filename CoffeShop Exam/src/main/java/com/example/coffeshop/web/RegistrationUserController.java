package com.example.coffeshop.web;

import com.example.coffeshop.model.dto.UserRegisterDTO;
import com.example.coffeshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegistrationUserController {

    private final UserService userService;

    @Autowired
    public RegistrationUserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @ModelAttribute("userRegisterDTO")
    public UserRegisterDTO initRegisterDTO() {
        return new UserRegisterDTO();
    }

    @PostMapping("register")
    public String register(@Valid UserRegisterDTO userRegisterDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.userService.registration(userRegisterDTO)) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            return "redirect:/register";
        }
        return "redirect:/login";
    }
}
