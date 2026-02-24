package com.example.tallerspring.servlet;

import com.example.tallerspring.model.Track;
import com.example.tallerspring.service.ArtistService;
import com.example.tallerspring.service.TrackService;
import com.example.tallerspring.views.TrackView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/track")
public class TrackServlet extends HttpServlet {

    private TrackService service;
    private ArtistService artistService;
    private TrackView view;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());

        service = context.getBean(TrackService.class);
        artistService = context.getBean(ArtistService.class);
        view = new TrackView();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null || action.equals("list")) {
            listTracks(resp);
        } else if (action.equals("form")) {
            showCreateForm(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action.equals("create")) {
            createTrack(req, resp);
        } else if (action.equals("delete")) {
            deleteTrack(req, resp);
        }
    }

    private void listTracks(HttpServletResponse resp) throws IOException {
        List<Track> tracks = service.getAllTracks();
        writeResponse(resp, view.listTracks(tracks));
    }

    private void showCreateForm(HttpServletResponse resp) throws IOException {
        writeResponse(resp, view.createTrackForm(artistService.getAllArtists()));
    }

    private void createTrack(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String title = req.getParameter("title");
        String genre = req.getParameter("genre");
        long duration = Long.parseLong(req.getParameter("duration"));
        String albumTitle = req.getParameter("albumTitle");

        String[] artistIdsParam = req.getParameterValues("artistIds");

        List<Long> artistIds =
                java.util.Arrays.stream(artistIdsParam)
                        .map(Long::parseLong)
                        .collect(Collectors.toList());

        service.createTrack(title, genre, duration, albumTitle, artistIds);

        resp.sendRedirect("track?action=list");
    }

    private void deleteTrack(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        long id = Long.parseLong(req.getParameter("id"));
        service.deleteTrack(id);

        resp.sendRedirect("track?action=list");
    }

    private void writeResponse(HttpServletResponse resp, String body) throws IOException {

        resp.setContentType("text/html");
        resp.getWriter().println("""
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset='UTF-8'>
                    <link rel='stylesheet' href='Styles/Track.css'>
                </head>
                <body>
                """ + body + """
                </body>
                </html>
                """);
    }
}