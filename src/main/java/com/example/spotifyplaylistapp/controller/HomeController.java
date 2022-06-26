package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;
import com.example.spotifyplaylistapp.model.entity.views.SongViewModel;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private UserService userService;
    private SongService songService;
    private LoggedUser loggedUser;

    @Autowired
    public HomeController(UserService userService, SongService songService, LoggedUser loggedUser) {
        this.userService = userService;
        this.songService = songService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/home")
    public String home(Model model){
        if (loggedUser.getId() == null){
            return "redirect:/users/login";
        }

        List<SongViewModel> pop = this.songService.getAllSongsByStyles(StyleEnum.POP);
        List<SongViewModel> jazz = this.songService.getAllSongsByStyles(StyleEnum.JAZZ);
        List<SongViewModel> rock = this.songService.getAllSongsByStyles(StyleEnum.ROCK);

        model.addAttribute("pop",pop);
        model.addAttribute("jazz",jazz);
        model.addAttribute("rock",rock);

        return "home";
    }
}
