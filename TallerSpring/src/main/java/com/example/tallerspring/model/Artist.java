package com.example.tallerspring.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Artist {

    private long id;
    private String name;
    private String nationality;

    private List<Track> tracks = new ArrayList<>();

    public Artist(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public Artist(){}

    public void addTrack(Track track) {
        if (!this.tracks.contains(track)) {
            this.tracks.add(track);
            track.addArtist(this);
        }
    }

    public void removeTrack(Track track) {
        if (this.tracks.contains(track)) {
            this.tracks.remove(track);
            track.getArtists().remove(this);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return id == artist.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
