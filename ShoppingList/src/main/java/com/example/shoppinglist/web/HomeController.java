package com.example.shoppinglist.web;

import com.example.shoppinglist.model.entity.enums.CategoryEnum;
import com.example.shoppinglist.model.entity.views.ProductViewModel;
import com.example.shoppinglist.services.ProductService;
import com.example.shoppinglist.services.UserService;
import com.example.shoppinglist.session.CurrentLoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    private UserService userService;
    private ProductService productService;
    private CurrentLoggedUser loggedUser;

    @Autowired
    public HomeController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/home")
    public String getHome(HttpSession httpSession,Model model){
        if (httpSession.getAttribute("something") == null){
            return "redirect:/login";
        }

        List<ProductViewModel> food = this.productService.getAllProductsByCategory(CategoryEnum.Food);
        List<ProductViewModel> drink = this.productService.getAllProductsByCategory(CategoryEnum.Drink);
        List<ProductViewModel> household = this.productService.getAllProductsByCategory(CategoryEnum.Household);
        List<ProductViewModel> other = this.productService.getAllProductsByCategory(CategoryEnum.Other);
        Double allProductsByPriceSum = this.productService.getAllProductsByPriceSum();

        model.addAttribute("food",food);
        model.addAttribute("drink",drink);
        model.addAttribute("household",household);
        model.addAttribute("other",other);
        model.addAttribute("sumOfProducts",String.format("%.2f",allProductsByPriceSum));

        return "home";
    }
}
