package com.examp.controllers;


import com.examp.models.dtos.ShipBattleDTO;
import com.examp.services.AuthenticationService;
import com.examp.services.ShipService;
import com.examp.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class HomeController {

    private AuthenticationService authenticationService;
    private LoggedUser loggedUser;
    private ShipService shipService;

    @Autowired
    public HomeController(AuthenticationService authenticationService, LoggedUser loggedUser, ShipService shipService) {
        this.authenticationService = authenticationService;
        this.loggedUser = loggedUser;
        this.shipService = shipService;
    }

    @GetMapping("")
    public String index(Model model) {
        if (loggedUser.getId() == 0) {
            return "index";
        }
        model.addAttribute("currentLoggedInUser", shipService.getShipsByLoggedUser(loggedUser.getId()));
        model.addAttribute("otherUserShips", shipService.getShipsByOtherUser(loggedUser.getId()));
        model.addAttribute("allShips", shipService.getAll());

        return "home";
    }

    @GetMapping("/home")
    public String home(){
        if (loggedUser.getId() == 0){
            return "index";
        }
        return "home";
    }

    @ModelAttribute
    public ShipBattleDTO shipBattleDTO() {
        return new ShipBattleDTO();
    }

    @PostMapping("/home/battle")
    public String afterBattle(@Valid ShipBattleDTO shipBattleDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", shipBattleDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipBattleDTO", bindingResult);
            return "redirect:/";
        }
        shipService.attack(shipBattleDTO.getAttacker(), shipBattleDTO.getDefender());
        return "redirect:/home";
    }
}
