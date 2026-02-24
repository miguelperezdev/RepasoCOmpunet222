package edu.co.icesi.service;

import edu.co.icesi.model.Device;

import edu.co.icesi.repository.DeviceRepository;
import edu.co.icesi.repository.MeasurementRepository;
import java.util.List;

public class DeviceService {

    private DeviceRepository deviceRepository;
    private MeasurementRepository measurementRepository;

    // Setters para inyección de dependencias
    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public void setMeasurementRepository(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public Device registerDevice(Device device) {
        // b: nombre no vacío
        if (device.getNombre() == null || device.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        // c: serialNumber al menos 5 caracteres
        if (device.getSerialNumber() == null || device.getSerialNumber().length() < 5) {
            throw new IllegalArgumentException("El número de serie debe tener al menos 5 caracteres");
        }
        // a: serialNumber único
        if (deviceRepository.findBySerialNumber(device.getSerialNumber()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un dispositivo con ese número de serie");
        }
        // Si no se envía estado, se asigna INACTIVE por defecto
        if (device.getEstate() == null) {
            device.setEstate("INACTIVE");
        }
        return deviceRepository.save(device);
    }

    public Device updateDeviceStatus(Integer deviceId, String newState) {
        if (!"ACTIVE".equals(newState) && !"INACTIVE".equals(newState)) {
            throw new IllegalArgumentException("El estado debe ser ACTIVE o INACTIVE");
        }
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new IllegalArgumentException("Dispositivo no encontrado"));
        device.setEstate(newState);
        return device;
    }

    public void deleteDevice(Integer deviceId) {
        if (measurementRepository.existsByDeviceId(deviceId)) {
            throw new IllegalArgumentException("No se puede eliminar un dispositivo con mediciones asociadas");
        }
        boolean removed = deviceRepository.delete(deviceId);
        if (!removed) {
            throw new IllegalArgumentException("Dispositivo no encontrado");
        }
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device getDeviceById(Integer id) {
        return deviceRepository.findById(id).orElse(null);
    }
}