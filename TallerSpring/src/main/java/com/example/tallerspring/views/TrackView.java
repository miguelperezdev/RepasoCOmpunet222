package com.example.tallerspring.views;

import com.example.tallerspring.model.Artist;
import com.example.tallerspring.model.Track;

import java.util.List;

public class TrackView {

    public String listTracks(List<Track> tracks) {

        StringBuilder builder = new StringBuilder();

        builder.append("<h2>Track Management</h2>");
        builder.append("<a href='track?action=form'>Create Track</a><br><br>");

        builder.append("<table border='1' cellpadding='5'>");
        builder.append("<tr>");
        builder.append("<th>ID</th>");
        builder.append("<th>Title</th>");
        builder.append("<th>Genre</th>");
        builder.append("<th>Album</th>");
        builder.append("<th>Duration</th>");
        builder.append("<th>Artists</th>");
        builder.append("<th>Delete</th>");
        builder.append("</tr>");

        for (Track track : tracks) {

            builder.append("<tr>");
            builder.append("<td>").append(track.getId()).append("</td>");
            builder.append("<td>").append(track.getTitle()).append("</td>");
            builder.append("<td>").append(track.getGenre()).append("</td>");
            builder.append("<td>").append(track.getAlbumTitle()).append("</td>");
            builder.append("<td>").append(track.getDuration()).append("</td>");

            builder.append("<td>");
            track.getArtists().forEach(a ->
                    builder.append(a.getName()).append(" "));
            builder.append("</td>");

            builder.append("<td>");
            builder.append("<form method='POST' action='track'>");
            builder.append("<input type='hidden' name='action' value='delete'/>");
            builder.append("<input type='hidden' name='id' value='")
                    .append(track.getId()).append("'/>");
            builder.append("<input type='submit' value='Delete'/>");
            builder.append("</form>");
            builder.append("</td>");

            builder.append("</tr>");
        }

        builder.append("</table>");

        return builder.toString();
    }

    public String createTrackForm(List<Artist> artists) {

        StringBuilder builder = new StringBuilder();

        builder.append("<h2>Create Track</h2>");
        builder.append("<form method='POST' action='track'>");

        builder.append("<input type='hidden' name='action' value='create'/>");

        builder.append("Title: <input type='text' name='title' required/><br><br>");
        builder.append("Genre: <input type='text' name='genre' required/><br><br>");
        builder.append("Album: <input type='text' name='albumTitle' required/><br><br>");
        builder.append("Duration: <input type='number' name='duration' required/><br><br>");

        builder.append("<h3>Select Artists:</h3>");

        for (Artist artist : artists) {
            builder.append("<input type='checkbox' name='artistIds' value='")
                    .append(artist.getId())
                    .append("'/> ")
                    .append(artist.getName())
                    .append("<br>");
        }

        builder.append("<br><input type='submit' value='Create'/>");
        builder.append("</form>");
        builder.append("<br><a href='track?action=list'>Back</a>");

        return builder.toString();
    }


}