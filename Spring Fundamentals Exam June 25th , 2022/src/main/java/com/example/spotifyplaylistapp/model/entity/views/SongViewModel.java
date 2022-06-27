package com.example.spotifyplaylistapp.model.entity.views;

import com.example.spotifyplaylistapp.model.entity.Style;

import java.math.BigDecimal;

public class SongViewModel {

    private Long id;
    private String performer;
    private String title;
    private BigDecimal duration;
    private Style style;

    public SongViewModel() {

    }

    public Long getId() {
        return id;
    }

    public SongViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public SongViewModel setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SongViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public SongViewModel setDuration(BigDecimal duration) {
        this.duration = duration;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public SongViewModel setStyle(Style style) {
        this.style = style;
        return this;
    }
}
