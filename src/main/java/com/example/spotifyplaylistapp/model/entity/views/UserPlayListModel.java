package com.example.spotifyplaylistapp.model.entity.views;

import java.math.BigDecimal;

public class UserPlayListModel {

    private Long id;

    private String song;

    private String title;

    private BigDecimal duration;

    public String getSong() {
        return song;
    }

    public UserPlayListModel setSong(String song) {
        this.song = song;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public UserPlayListModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public UserPlayListModel setDuration(BigDecimal duration) {
        this.duration = duration;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserPlayListModel setId(Long id) {
        this.id = id;
        return this;
    }
}
