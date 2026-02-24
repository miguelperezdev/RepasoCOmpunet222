package com.example.tallerspring.servlet;

import com.example.tallerspring.model.Artist;
import com.example.tallerspring.service.ArtistService;
import com.example.tallerspring.views.ArtistView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.List;

@WebServlet("/artist")
public class ArtistServlet extends HttpServlet {

    private ArtistService service;
    private ArtistView view;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());

        service = context.getBean(ArtistService.class);
        view = new ArtistView();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null || action.equals("list")) {
            listArtists(resp);
        } else if (action.equals("form")) {
            showCreateForm(resp);
        } else if (action.equals("search")) {
            searchArtist(req, resp);
        }
        else if (action.equals("searchForm")) {
            showSearchForm(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action.equals("create")) {
            createArtist(req, resp);
        } else if (action.equals("delete")) {
            deleteArtist(req, resp);
        }
    }

    private void listArtists(HttpServletResponse resp) throws IOException {
        List<Artist> artists = service.getAllArtists();
        writeResponse(resp, view.listArtists(artists));
    }

    private void showCreateForm(HttpServletResponse resp) throws IOException {
        writeResponse(resp, view.createArtistForm());
    }

    private void showSearchForm(HttpServletResponse resp) throws IOException {
        writeResponse(resp, view.searchArtistForm());
    }

    private void createArtist(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String name = req.getParameter("name");
        String nationality = req.getParameter("nationality");

        service.createArtist(name, nationality);

        resp.sendRedirect("artist?action=list");
    }

    private void searchArtist(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String name = req.getParameter("name");
        Artist artist = service.getArtistByName(name);

        writeResponse(resp, view.showArtistDetails(artist));
    }

    private void deleteArtist(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        long id = Long.parseLong(req.getParameter("id"));
        service.deleteArtist(id);

        resp.sendRedirect("artist?action=list");
    }

    private void writeResponse(HttpServletResponse resp, String body) throws IOException {

        resp.setContentType("text/html");
        resp.getWriter().println("""
                <!DOCTYPE html>
                <html>
                <title>Artist Management</title>
                <head>
                    <meta charset='UTF-8'>
                    <link rel='stylesheet' href='Styles/Artist.css'>
                </head>
                <body>
                """ + body + """
                </body>
                </html>
                """);
    }
}