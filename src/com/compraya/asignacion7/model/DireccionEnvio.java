package com.compraya.asignacion7.model;

public class DireccionEnvio {
    private String calle;
    private String ciudad;
    private String departamento;
    private String codigoPostal;
    private String pais;

    public DireccionEnvio(String calle, String ciudad, String departamento, String codigoPostal, String pais) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
    }

    public String getCalle() { return calle; }
    public String getCiudad() { return ciudad; }
    public String getDepartamento() { return departamento; }

    @Override
    public String toString() {
        return String.format("%s, %s, %s (%s) - %s", calle, ciudad, departamento, codigoPostal, pais);
    }
}
