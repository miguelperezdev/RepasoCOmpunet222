package edu.co.icesi.model;

public class Device {

    private Integer id;
    private String nombre;
    private String serialNumber;       // único
    private String ubicacion;           // "Ubicación"
    private String tipo;
    private String estate;

    public Device() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstate() { return estate; }
    public void setEstate(String estate) { this.estate = estate; }
}
