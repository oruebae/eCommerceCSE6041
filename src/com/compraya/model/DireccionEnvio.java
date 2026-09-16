package com.compraya.model;

/**
 * Representa la dirección de entrega asignada a un Pedido.
 */
public class DireccionEnvio {
    private String calle;
    private String ciudad;
    private String departamento;
    private String codigoPostal;
    private String pais;

    public DireccionEnvio() {
        this.pais = "Colombia";
    }

    public DireccionEnvio(String calle, String ciudad, String departamento, String codigoPostal, String pais) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s (%s) - %s", calle, ciudad, departamento, codigoPostal, pais);
    }
}
