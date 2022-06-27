package com.example.spotifyplaylistapp.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "songs")
public class Song extends BaseEntity{

    @Column(nullable = false)
    private String performer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private BigDecimal duration;

    private LocalDate releaseDate;

    @ManyToOne
    private Style style;

    @ManyToMany(mappedBy = "playlist",targetEntity = User.class)
    private List<User> user;

    public Song() {

    }

    public String getPerformer() {
        return performer;
    }

    public Song setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Song setTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public Song setDuration(BigDecimal duration) {
        this.duration = duration;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Song setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public Song setStyle(Style style) {
        this.style = style;
        return this;
    }

    public List<User> getUser() {
        return user;
    }

    public Song setUser(List<User> user) {
        this.user = user;
        return this;
    }
}
