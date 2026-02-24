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
public class TrackRepository {

    private Map<Long, Track> tracks = new HashMap<>();
    private long currentId;

    @PostConstruct
    public void init() {

    }

    public List<Track> findAll() {
        return new ArrayList<>(tracks.values());
    }

    public Track findById(long id) {
        return tracks.get(id);
    }

    public Track findByTitle(String title) {
        return tracks.values().stream().filter(t -> t.getTitle().equals(title)).findFirst().orElse(null);
    }

    public Track save(Track track) {
        currentId++;
        track.setId(currentId);
        tracks.put(currentId, track);
        return track;
    }

    public void deleteById(long id) {
        tracks.remove(id);
    }

}
