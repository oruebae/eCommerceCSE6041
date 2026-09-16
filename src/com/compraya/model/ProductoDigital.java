package com.compraya.model;

/**
 * Subclase ProductoDigital que extiende de Producto (Herencia).
 * Sobrescribe mostrarDetalle() para imprimir especificaciones de formato, tamaño, licenciamiento y entrega digital (Sobreescritura - Overriding).
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
     * Sobreescritura del método mostrarDetalle() para bienes digitales (Overriding).
     */
    @Override
    public void mostrarDetalle() {
        System.out.println("-------------------------------------------------------");
        System.out.println(" 💾 FICHA TÉCNICA DE PRODUCTO DIGITAL                  ");
        System.out.println("-------------------------------------------------------");
        System.out.println("ID:            " + getId());
        System.out.println("Nombre:        " + getNombre());
        System.out.println("Descripción:   " + getDescripcion());
        System.out.printf("Precio:        $%.2f\n", getPrecio());
        System.out.println("Stock:         " + getStock() + " licencias disponibles");
        System.out.println("Categoría:     " + (getCategoria() != null ? getCategoria().getNombre() : "N/A"));
        System.out.println("Formato:       " + formatoArchivo);
        System.out.printf("Tamaño MB:     %.1f MB\n", tamanoMB);
        System.out.println("Licencia:      " + licencia);
        System.out.println("URL Base:      " + urlDescarga);
        System.out.println("Entrega:       Inmediata vía enlace dinámico con token");
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
        return false;
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
