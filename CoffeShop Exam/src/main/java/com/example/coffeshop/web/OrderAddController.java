package com.example.coffeshop.web;


import com.example.coffeshop.model.dto.AddOrderDTO;
import com.example.coffeshop.services.OrderService;
import com.example.coffeshop.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OrderAddController {

    private final OrderService orderService;
    private final LoggedUser loggedUser;

    @Autowired
    public OrderAddController(OrderService orderService, LoggedUser loggedUser) {
        this.orderService = orderService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/order/add")
    public String order() {
        if (loggedUser.getId() == 0){
            return "redirect:/login";
        }
        return "order-add";
    }

    @ModelAttribute("addOrderDTO")
    public AddOrderDTO initAddOrderDTO(){
        return new AddOrderDTO();
    }

    @PostMapping("order/add")
    public String orderAdd(@Valid AddOrderDTO addOrderDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.orderService.addOrder(addOrderDTO)){
            redirectAttributes.addFlashAttribute("addOrderDTO",addOrderDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOrderDTO",bindingResult);
            return "redirect:/order/add";
        }
        return "redirect:/home";
    }

    @GetMapping("orders/ready/{id}")
    public String ready(@PathVariable Long id){
        orderService.readyOrder(id);

        return "redirect:/";
    }
}
