package edu.co.icesi.servlet;

import edu.co.icesi.model.Device;
import edu.co.icesi.service.DeviceService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet("/addDevice")
public class AddDeviceServlet extends HttpServlet {

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

        String nombre = req.getParameter("nombre");
        String serial = req.getParameter("serialNumber");
        String ubicacion = req.getParameter("ubicacion");
        String tipo = req.getParameter("tipo");
        String estate = req.getParameter("estate");

        Device device = new Device();
        device.setNombre(nombre);
        device.setSerialNumber(serial);
        device.setUbicacion(ubicacion);
        device.setTipo(tipo);
        device.setEstate(estate);

        try {
            deviceService.registerDevice(device);
            resp.sendRedirect(req.getContextPath() + "/listDevices");
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("errorMessage", e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/src/webapp/add-device.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/src/webapp/add-device.html");
    }
}