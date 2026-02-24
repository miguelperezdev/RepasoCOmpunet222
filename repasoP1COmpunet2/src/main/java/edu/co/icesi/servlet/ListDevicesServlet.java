package edu.co.icesi.servlet;

import edu.co.icesi.model.Device;
import edu.co.icesi.service.DeviceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/listDevices")
public class ListDevicesServlet extends HttpServlet {

    private DeviceService deviceService;

    @Override
    public void init() {
        deviceService = edu.co.icesi.context.AppContext
                .getContext()
                .getBean(DeviceService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Device> devices = deviceService.getAllDevices();
        req.setAttribute("devices", devices);

        req.getRequestDispatcher("/WEB-INF/views/list-devices.jsp").forward(req, resp);
    }
}
