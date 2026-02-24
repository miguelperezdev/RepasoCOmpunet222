package com.example.tallerspring.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Track {

    private long id;
    private String title;
    private String genre;
    private long duration;
    private String albumTitle;

    private List<Artist> artists = new ArrayList<>();

    public Track(){}

    public Track(String title, String genre, long duration, String albumTitle) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.albumTitle = albumTitle;
    }

    public void addArtist(Artist artist) {
        if (!this.artists.contains(artist)) {
            this.artists.add(artist);
            if (!artist.getTracks().contains(this)) {
                artist.getTracks().add(this);
            }
        }
    }

    public void removeArtist(Artist artist) {
        if (this.artists.contains(artist)) {
            this.artists.remove(artist);
            artist.getTracks().remove(this);
        }
    }

    public String toString(){

        return "Title: " + this.title +
                "Genre: " + this.genre +
                "Duration: " + this.duration +
                "Album title: " + this.albumTitle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public long getDuration() {
        return duration;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track)) return false;
        Track track = (Track) o;
        return id == track.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
