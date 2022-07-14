package com.example.musicdb.respositories;

import com.example.musicdb.models.entities.Artist;
import com.example.musicdb.models.entities.enums.ArtistBand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Long> {

    Artist findByName(ArtistBand artist);
}
