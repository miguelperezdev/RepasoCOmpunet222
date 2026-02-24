package edu.co.icesi.servlet;


import edu.co.icesi.service.DeviceService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/update")
public class UpdateDeviceStatusServlet extends HttpServlet {

    private DeviceService deviceService;

    @Override
    public void init() {
        deviceService = edu.co.icesi.context.AppContext
                .getContext()
                .getBean(DeviceService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        String newState = req.getParameter("estate");

        if (idParam == null || newState == null) {
            req.getSession().setAttribute("errorMessage", "Faltan parámetros requeridos");
            resp.sendRedirect(req.getContextPath() + "/src/webapp/update-status.html");
            return;
        }

        try {
            Integer id = Integer.parseInt(idParam);
            deviceService.updateDeviceStatus(id, newState);
            req.getSession().setAttribute("successMessage", "Estado actualizado correctamente");
            resp.sendRedirect(req.getContextPath() + "/listDevices");
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("errorMessage", "ID inválido");
            resp.sendRedirect(req.getContextPath() + "/src/webapp/update-status.html");
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("errorMessage", e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/src/webapp/update-status.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/src/webapp/update-status.html");
    }
}