package com.example.coffeshop.web;

import com.example.coffeshop.model.dto.UserLoginDTO;
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
public class LoginUserController {

    private UserService userService;

    @Autowired
    public LoginUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @ModelAttribute("userLoginDTO")
    public UserLoginDTO initUserLogin(){
        return new UserLoginDTO();
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginDTO",userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO",bindingResult);
            return "redirect:/login";
        }
        if (!userService.login(userLoginDTO)){
            redirectAttributes.addFlashAttribute("userLoginDTO",userLoginDTO);
            redirectAttributes.addFlashAttribute("wrongCredentials",true);
            return "redirect:/login";
        }
        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(){
        this.userService.logout();
        return "redirect:/";
    }
}
