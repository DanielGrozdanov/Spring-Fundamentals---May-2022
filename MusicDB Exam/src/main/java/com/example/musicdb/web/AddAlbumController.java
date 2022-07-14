package com.example.musicdb.web;

import com.example.musicdb.models.entities.dtos.AddAlbumDTO;
import com.example.musicdb.models.session.LoggedUser;
import com.example.musicdb.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller

public class AddAlbumController {

    private AlbumService albumService;
    private LoggedUser loggedUser;

    @Autowired
    public AddAlbumController(AlbumService albumService, LoggedUser loggedUser) {
        this.albumService = albumService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("album/add")
    public String addAlbum(){
        if (loggedUser.getId() == null){
            return "redirect:/users/login";
        }
        return "add-album";
    }
    @PostMapping("album/add")
    public String addAlbum(@Valid AddAlbumDTO addAlbumDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !albumService.addAlbum(addAlbumDTO)) {
            redirectAttributes.addFlashAttribute("addAlbumDTO", addAlbumDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addAlbumDTO", bindingResult);
           return "redirect:/album/add";
        }
        return "redirect:/home";
    }
    @ModelAttribute("addAlbumDTO")
    public AddAlbumDTO initAlbumDTO(){
        return new AddAlbumDTO();
    }
}
