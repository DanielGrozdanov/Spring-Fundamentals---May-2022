package com.example.musicdb.seeding;

import com.example.musicdb.services.ArtistService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeding implements CommandLineRunner {

    private final ArtistService artistService;

    public DatabaseSeeding(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Override
    public void run(String... args) throws Exception {
        artistService.seed();
    }
}
