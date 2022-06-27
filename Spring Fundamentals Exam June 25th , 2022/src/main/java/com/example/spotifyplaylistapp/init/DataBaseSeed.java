package com.example.spotifyplaylistapp.init;

import com.example.spotifyplaylistapp.service.StyleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseSeed implements CommandLineRunner {

    private final StyleService styleService;

    public DataBaseSeed(StyleService styleService) {
        this.styleService = styleService;
    }

    @Override
    public void run(String... args) throws Exception {
            styleService.seedStyle();
    }
}
