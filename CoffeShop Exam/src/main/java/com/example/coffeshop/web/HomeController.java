package com.example.coffeshop.web;

import com.example.coffeshop.model.views.UserViewOrderDTO;
import com.example.coffeshop.model.views.ViewOrderDTO;
import com.example.coffeshop.services.OrderService;
import com.example.coffeshop.services.UserService;
import com.example.coffeshop.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final OrderService orderService;
    private final LoggedUser loggedUser;
    private final UserService userService;

    @Autowired
    public HomeController(OrderService orderService, LoggedUser loggedUser, UserService userService) {
        this.orderService = orderService;
        this.loggedUser = loggedUser;
        this.userService = userService;
    }


    @GetMapping("home")
    public String home(Model model){
        if (loggedUser.getId() == 0){
            return "index";
        }

        List<ViewOrderDTO> orders = orderService.findAllOrdersOrderedByPriceDesc();

        model.addAttribute("orders", orders);
        model.addAttribute("totalTime",orders
                .stream()
                .map(viewOrderDTO -> viewOrderDTO.getCategory().getNeededTime())
                .reduce(Integer::sum)
                .orElse(0));
        model.addAttribute("users",userService.findAllUsersAndCountOfOrdersDesc());

        return "home";
    }

}