package com.compraya.model;

/**
 * Subclase ProductoDigital que extiende de Producto (Herencia).
 * Especializa la funcionalidad para bienes intangibles (software, e-books, licencias, cursos)
 * que se entregan mediante descargas o licencias digitales sin requerir flete físico.
 */
public class ProductoDigital extends Producto {
    private String formatoArchivo; // Ej: "PDF", "ZIP", "MP4", "KEY"
    private double tamanoMB;
    private String urlDescarga;
    private String licencia;

    public ProductoDigital() {
        super();
    }

    /**
     * Constructor completo para ProductoDigital.
     */
    public ProductoDigital(int id, String nombre, String descripcion, double precio, int stock,
                           Categoria categoria, String formatoArchivo, double tamanoMB,
                           String urlDescarga, String licencia) {
        super(id, nombre, descripcion, precio, stock, categoria);
        this.formatoArchivo = formatoArchivo;
        this.tamanoMB = tamanoMB;
        this.urlDescarga = urlDescarga;
        this.licencia = licencia;
    }

    /**
     * Genera y retorna un token único de descarga segura para el cliente.
     * 
     * @return URL de descarga personalizada con token.
     */
    public String generarEnlaceDescarga() {
        String token = Integer.toHexString((int) (Math.random() * 1000000));
        return String.format("%s?token=%s&licence=%s", urlDescarga, token, licencia);
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return String.format("Digital [Formato: %s | Tamaño: %.1f MB | Licencia: %s]",
                formatoArchivo, tamanoMB, licencia);
    }

    @Override
    public boolean requiereEnvioFisico() {
        return false; // Los productos digitales no requieren envío físico por transportadora
    }

    // --- Getters y Setters ---

    public String getFormatoArchivo() {
        return formatoArchivo;
    }

    public void setFormatoArchivo(String formatoArchivo) {
        this.formatoArchivo = formatoArchivo;
    }

    public double getTamanoMB() {
        return tamanoMB;
    }

    public void setTamanoMB(double tamanoMB) {
        this.tamanoMB = tamanoMB;
    }

    public String getUrlDescarga() {
        return urlDescarga;
    }

    public void setUrlDescarga(String urlDescarga) {
        this.urlDescarga = urlDescarga;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }
}
