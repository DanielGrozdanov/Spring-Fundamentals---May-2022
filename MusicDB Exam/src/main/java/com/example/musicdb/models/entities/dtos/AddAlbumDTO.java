package com.example.musicdb.models.entities.dtos;


import com.example.musicdb.models.entities.enums.ArtistBand;
import com.example.musicdb.models.entities.enums.Genre;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AddAlbumDTO {

    @NotBlank
    @Size(min = 3,max = 20)
    private String albumName;

    @NotBlank
    @Size(min = 5)
    private String imageUrl;

    @Positive
    private BigDecimal price;


    @Min(10)
    private int copies;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private String producer;

    @NotNull
    private ArtistBand artist;

    @NotNull
    private Genre genre;

    @NotBlank
    @Size(min = 5)
    private String description;

    public AddAlbumDTO() {

    }

    public String getAlbumName() {
        return albumName;
    }

    public AddAlbumDTO setAlbumName(String albumName) {
        this.albumName = albumName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddAlbumDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddAlbumDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getCopies() {
        return copies;
    }

    public AddAlbumDTO setCopies(int copies) {
        this.copies = copies;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AddAlbumDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public AddAlbumDTO setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public ArtistBand getArtist() {
        return artist;
    }

    public AddAlbumDTO setArtist(ArtistBand artist) {
        this.artist = artist;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public AddAlbumDTO setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddAlbumDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
