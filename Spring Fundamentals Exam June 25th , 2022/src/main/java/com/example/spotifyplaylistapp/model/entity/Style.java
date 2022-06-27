package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    private StyleEnum name;

    private String description;

    public Style() {
    }

    public StyleEnum getName() {
        return name;
    }

    public Style setName(StyleEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Style setDescription(String description) {
        this.description = description;
        return this;
    }

}
