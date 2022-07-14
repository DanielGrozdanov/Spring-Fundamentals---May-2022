package com.example.musicdb.models.entities;

import com.example.musicdb.models.entities.enums.ArtistBand;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artist extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private ArtistBand name;

    @Column(columnDefinition = "TEXT")
    private String careerInformation;

    public Artist() {

    }

    public ArtistBand getName() {
        return name;
    }

    public Artist setName(ArtistBand name) {
        this.name = name;
        return this;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public Artist setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }
}
