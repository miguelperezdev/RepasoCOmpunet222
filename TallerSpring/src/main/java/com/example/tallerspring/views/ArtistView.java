package com.example.tallerspring.views;

import com.example.tallerspring.model.Artist;
import com.example.tallerspring.model.Track;

import java.util.List;

public class ArtistView {

    public String listArtists(List<Artist> artists) {

        StringBuilder builder = new StringBuilder();

        builder.append("<h2>Artist Management</h2>");

        // Botones de navegación
        builder.append("<a href='artist?action=form'>Create Artist</a> | ");
        builder.append("<a href='artist?action=searchForm'>Search Artist</a><br><br>");

        builder.append("<table border='1' cellpadding='5'>");
        builder.append("<tr>");
        builder.append("<th>ID</th>");
        builder.append("<th>Name</th>");
        builder.append("<th>Nationality</th>");
        builder.append("<th>#Tracks</th>");
        builder.append("<th>Delete</th>");
        builder.append("</tr>");

        for (Artist artist : artists) {

            builder.append("<tr>");
            builder.append("<td>").append(artist.getId()).append("</td>");
            builder.append("<td>").append(artist.getName()).append("</td>");
            builder.append("<td>").append(artist.getNationality()).append("</td>");
            builder.append("<td>").append(artist.getTracks().size()).append("</td>");

            // Formulario eliminar
            builder.append("<td>");
            builder.append("<form method='POST' action='artist'>");
            builder.append("<input type='hidden' name='action' value='delete'/>");
            builder.append("<input type='hidden' name='id' value='")
                    .append(artist.getId()).append("'/>");
            builder.append("<input type='submit' value='Delete'/>");
            builder.append("</form>");
            builder.append("</td>");

            builder.append("</tr>");
        }

        builder.append("</table>");

        return builder.toString();
    }

    public String createArtistForm() {
        return """
                <h2>Create Artist</h2>
                <form method='POST' action='artist'>
                    <input type='hidden' name='action' value='create'/>

                    Name: <input type='text' name='name' required/><br><br>
                    Nationality: <input type='text' name='nationality' required/><br><br>

                    <input type='submit' value='Create'/>
                </form>
                <br>
                <a href='artist?action=list'>Back</a>
                """;
    }

    public String searchForm() {
        return """
                <h2>Search Artist</h2>
                <form method='GET' action='artist'>
                    <input type='hidden' name='action' value='search'/>
                    Name: <input type='text' name='name' required/>
                    <input type='submit' value='Search'/>
                </form>
                <br>
                <a href='artist?action=list'>Back</a>
                """;
    }

    public String showArtistDetails(Artist artist) {

        if (artist == null) {
            return "<h3>Artist not found</h3><a href='artist?action=list'>Back</a>";
        }

        StringBuilder builder = new StringBuilder();

        builder.append("<h2>Artist Details</h2>");
        builder.append("<p><strong>Name:</strong> ")
                .append(artist.getName()).append("</p>");
        builder.append("<p><strong>Nationality:</strong> ")
                .append(artist.getNationality()).append("</p>");

        builder.append("<h3>Tracks:</h3>");
        builder.append("<ul>");

        for (Track track : artist.getTracks()) {
            builder.append("<li>")
                    .append(track.getTitle())
                    .append(" (")
                    .append(track.getAlbumTitle())
                    .append(")")
                    .append("</li>");
        }

        builder.append("</ul>");
        builder.append("<a href='artist?action=list'>Back</a>");

        return builder.toString();
    }

    public String searchArtistForm() {

        return """
        <div class="search-container">
            <h2>Search Artist</h2>
        
            <form action="artist" method="get" class="search-form">
        
                <input type="hidden" name="action" value="search">
        
                <div class="form-group">
                    <label for="name">Artist Name:</label>
                    <input type="text" id="name" name="name" required>
                </div>
        
                <button type="submit" class="btn-search">Search</button>
                <a href="artist?action=list" class="btn-back">Back</a>
        
            </form>
        </div>
        """;
    }

}