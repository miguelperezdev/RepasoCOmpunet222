package com.example.tallerspring.service;

import com.example.tallerspring.model.Artist;
import com.example.tallerspring.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist createArtist(String name, String nationality) {
        Artist artist = new Artist(name, nationality);
        return artistRepository.save(artist);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Artist getArtistByName(String name) {
        return artistRepository.findByName(name);
    }

    public void deleteArtist(long id) {
        artistRepository.deleteById(id);
    }
}
