package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.dto.AddSongDTO;
import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;
import com.example.spotifyplaylistapp.model.entity.views.SongViewModel;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {
    private SongRepository songRepository;
    private ModelMapper modelMapper;
    private StyleService styleService;
    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public SongService(SongRepository songRepository, ModelMapper modelMapper, StyleService styleService, UserService userService, UserRepository userRepository) {
        this.songRepository = songRepository;
        this.modelMapper = modelMapper;
        this.styleService = styleService;
        this.userService = userService;
        this.userRepository = userRepository;
    }
    public boolean addSong(AddSongDTO addSongDTO){
        Optional<Song> byPerformer = songRepository.findByPerformer(addSongDTO.getPerformer());
        if (byPerformer.isPresent()){
            return false;
        }

        Song song = modelMapper.map(addSongDTO,Song.class);
        song.setStyle(styleService.findStyleByName(addSongDTO.getStyle()));
        this.songRepository.save(song);
        return true;
    }

    public List<SongViewModel> getAllSongsByStyles(StyleEnum styleEnum){
        Style style = this.styleService.findStyleByName(styleEnum);
        List<Song> allSongsByStyle = this.songRepository.findAllByStyle(style);
         return allSongsByStyle.stream().map(song -> modelMapper.map(song,SongViewModel.class)).collect(Collectors.toList());
    }

    public List<Song> userPlayList(Long id){
        return null;
    }
    public Optional<Song> addSongToPlayList(Long id) {
        return this.songRepository.findById(id);
    }
}
