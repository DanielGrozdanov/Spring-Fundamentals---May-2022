package com.example.musicdb.services;


import com.example.musicdb.models.entities.Album;
import com.example.musicdb.models.entities.User;
import com.example.musicdb.models.entities.dtos.AddAlbumDTO;
import com.example.musicdb.models.session.LoggedUser;
import com.example.musicdb.respositories.AlbumRepository;
import com.example.musicdb.respositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumService {
    private AlbumRepository albumRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private ArtistService artistService;
    private LoggedUser loggedUser;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, ModelMapper modelMapper, UserRepository userRepository, ArtistService artistService, LoggedUser loggedUser) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.artistService = artistService;

        this.loggedUser = loggedUser;
    }
    public boolean addAlbum(AddAlbumDTO addAlbumDTO){
        Optional<User> user = this.userRepository.findById(loggedUser.getId());
        Album album = modelMapper.map(addAlbumDTO,Album.class);
        album.setArtist(artistService.getArtist(addAlbumDTO.getArtist()));
        album.setAddedFrom(user.get());
        this.albumRepository.save(album);
        return true;
    }
}
