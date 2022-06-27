package com.example.spotifyplaylistapp.service;


import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StyleService {

    private final StyleRepository styleRepository;

    @Autowired
    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public void seedStyle(){
        if(styleRepository.count() == 0){
            Arrays.stream(StyleEnum.values()).forEach(styleEnum -> {
                    Style style = new Style();
                    style.setName(styleEnum);
                    styleRepository.save(style);
            });
        }
    }

    public Style findStyleByName(StyleEnum styleEnum) {
      return this.styleRepository.findByName(styleEnum);
    }
}
