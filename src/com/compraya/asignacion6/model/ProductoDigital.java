package com.compraya.asignacion6.model;

public class ProductoDigital extends Producto {
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

    public String generarEnlaceDescarga() {
        String token = Integer.toHexString((int) (Math.random() * 1000000));
        return String.format("%s?token=%s&licence=%s", urlDescarga, token, licencia);
    }

    @Override
    public void mostrarDetalle() {
        System.out.printf("ProductoDigital[ID: %d | %s | $%.2f | Formato: %s | %.1f MB | Lic: %s]\n",
                getId(), getNombre(), getPrecio(), formatoArchivo, tamanoMB, licencia);
    }

    @Override
    public boolean requiereEnvioFisico() { return false; }

    @Override
    public String obtenerDetallesEspecificos() {
        return String.format("Digital [Formato: %s | Tamaño: %.1f MB]", formatoArchivo, tamanoMB);
    }

    public String getFormatoArchivo() { return formatoArchivo; }
    public void setFormatoArchivo(String formatoArchivo) { this.formatoArchivo = formatoArchivo; }
    public double getTamanoMB() { return tamanoMB; }
    public void setTamanoMB(double tamanoMB) { this.tamanoMB = tamanoMB; }
    public String getUrlDescarga() { return urlDescarga; }
    public void setUrlDescarga(String urlDescarga) { this.urlDescarga = urlDescarga; }
    public String getLicencia() { return licencia; }
    public void setLicencia(String licencia) { this.licencia = licencia; }
}
