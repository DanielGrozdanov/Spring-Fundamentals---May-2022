package com.examp.controllers;

import com.examp.models.dtos.CreateShipDTO;
import com.examp.services.ShipService;
import com.examp.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ShipController {


    private final ShipService shipService;
    private final LoggedUser loggedUser;

    @Autowired
    public ShipController(ShipService shipService, LoggedUser loggedUser) {
        this.shipService = shipService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("shipDTO")
    public CreateShipDTO initCreateShipDTO() {
        return new CreateShipDTO();
    }

    @GetMapping("/ships/add")
    public String ships() {
        if(loggedUser.getId() == 0){
            return "redirect:/login";
        }
        return "ship-add";
    }

    @PostMapping("/ships/add")
    public String ships(@Valid CreateShipDTO shipDTO,BindingResult
            bindingResult,RedirectAttributes
            redirectAttributes){

        if (bindingResult.hasErrors() || !this.shipService.create(shipDTO)){
            redirectAttributes.addFlashAttribute("shipDTO", shipDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipDTO", bindingResult);
            return "redirect:/ships/add";
        }
        return "redirect:/home";
    }

}