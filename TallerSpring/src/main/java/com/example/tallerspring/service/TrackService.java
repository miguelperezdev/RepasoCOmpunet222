package com.example.tallerspring.service;

import com.example.tallerspring.model.Artist;
import com.example.tallerspring.model.Track;
import com.example.tallerspring.repository.ArtistRepository;
import com.example.tallerspring.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private ArtistRepository artistRepository;

    public void setTrackRepository(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Track createTrack(String title, String genre, long duration, String albumTitle, List<Long> artistIds) {

        Track track = new Track(title, genre, duration, albumTitle);
        trackRepository.save(track);

        for (Long id : artistIds) {
            Artist artist = artistRepository.findById(id);
            if (artist != null) {
                artist.addTrack(track);
            }
        }

        return track;
    }

    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    public void deleteTrack(long id) {
        Track track = trackRepository.findById(id);

        if (track != null) {
            for (Artist artist : track.getArtists()) {
                artist.removeTrack(track);
            }
            trackRepository.deleteById(id);
        }
    }
}
