package com.example.tallerspring.repository;

import com.example.tallerspring.model.Artist;
import com.example.tallerspring.model.Track;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ArtistRepository {

    private Map<Long, Artist> artists = new HashMap<>();
    private long currentId;

    @PostConstruct
    public void init() {

    }

    public List<Artist> findAll() {
        return new ArrayList<>(artists.values());
    }

    public Artist findById(long id) {
        return artists.get(id);
    }

    public Artist findByName(String name) {
        return artists.values().stream().filter(a -> a.getName().equals(name)).findFirst().orElse(null);
    }

    public Artist save(Artist artist) {
        currentId++;
        artist.setId(currentId);
        artists.put(currentId, artist);
        return artist;
    }

    public void deleteById(long id) {
        artists.remove(id);
    }


}
