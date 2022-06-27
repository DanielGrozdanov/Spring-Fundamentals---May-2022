package com.example.spotifyplaylistapp.model.entity.dto;


import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AddSongDTO {

    @NotBlank
    @Size(min = 3,max = 20)
    private String performer;

    @NotBlank
    @Size(min = 2,max = 20)
    private String title;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @Positive
    private BigDecimal duration;

    @NotNull
    private StyleEnum style;

    public AddSongDTO() {

    }

    public String getPerformer() {
        return performer;
    }

    public AddSongDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AddSongDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AddSongDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public AddSongDTO setDuration(BigDecimal duration) {
        this.duration = duration;
        return this;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public AddSongDTO setStyle(StyleEnum style) {
        this.style = style;
        return this;
    }
}
