package com.compraya.asignacion7.model;

public class ProductoDigital extends Item {
    private String formatoArchivo;
    private double tamanoMB;
    private String urlDescarga;
    private String licencia;

    public ProductoDigital() { super(); }

    public ProductoDigital(int id, String nombre, String descripcion, double precio, int stock,
                           Categoria categoria, String formatoArchivo, double tamanoMB,
                           String urlDescarga, String licencia) {
        super(id, nombre, descripcion, precio, stock, categoria);
        this.formatoArchivo = formatoArchivo;
        this.tamanoMB = tamanoMB;
        this.urlDescarga = urlDescarga;
        this.licencia = licencia;
    }

    @Override
    public void mostrarDetalle() {
        System.out.printf("ProductoDigital[ID: %d | %s | $%.2f | Formato: %s | Lic: %s]\n",
                getId(), getNombre(), getPrecio(), formatoArchivo, licencia);
    }

    @Override
    public boolean requiereEnvioFisico() { return false; }

    @Override
    public String obtenerDetallesEspecificos() {
        return String.format("Digital [Formato: %s | Tamaño: %.1f MB]", formatoArchivo, tamanoMB);
    }

    public String getFormatoArchivo() { return formatoArchivo; }
    public double getTamanoMB() { return tamanoMB; }
    public String getUrlDescarga() { return urlDescarga; }
    public String getLicencia() { return licencia; }
}
