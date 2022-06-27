package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.entity.dto.AddSongDTO;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.session.LoggedUser;
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
public class AddSongController {

    private SongService songService;
    private LoggedUser loggedUser;

    @Autowired
    public AddSongController(SongService songService, LoggedUser loggedUser) {
        this.songService = songService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("song/add")
    public String song(){
        if (this.loggedUser.getId() == null){
            return "redirect:/users/login";
        }
        return "song-add";
    }

    @ModelAttribute("songByPerformerAlreadyExists")
    public boolean checkIfSongExists(){
        return false;
    }

    @PostMapping("song/add")
    public String addSong(@Valid AddSongDTO addSongDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addSongDTO", addSongDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSongDTO", bindingResult);
            return "redirect:/song/add";
        }
        if (!this.songService.addSong(addSongDTO)){
            redirectAttributes.addFlashAttribute("addSongDTO", addSongDTO);
            redirectAttributes.addFlashAttribute("songByPerformerAlreadyExists", true);
            return "redirect:/song/add";
        }

        return "home";
    }

    @GetMapping("/add/{id}")
    public String addSongToPlayList(@PathVariable Long id) {
        this.songService.addSongToPlayList(id);
        return "redirect:/home";
    }
    @ModelAttribute()
    public AddSongDTO initSongDTO(){
        return new AddSongDTO();
    }
}
