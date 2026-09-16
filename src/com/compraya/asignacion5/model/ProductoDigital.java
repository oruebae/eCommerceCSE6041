package com.compraya.asignacion5.model;

/**
 * Subclase ProductoDigital (Asignación 5) que extiende Producto e implementa la interfaz Licenciable.
 */
public class ProductoDigital extends Producto implements Licenciable {
    private String formatoArchivo;
    private double tamanoMB;
    private String urlDescarga;
    private String licencia;

    public ProductoDigital() {
        super();
    }

    public ProductoDigital(int id, String nombre, String descripcion, double precio, int stock,
                           Categoria categoria, String formatoArchivo, double tamanoMB,
                           String urlDescarga, String licencia) {
        super(id, nombre, descripcion, precio, stock, categoria);
        this.formatoArchivo = formatoArchivo;
        setTamanoMB(tamanoMB);
        this.urlDescarga = urlDescarga;
        this.licencia = licencia;
    }

    @Override
    public String generarEnlaceDescarga() {
        String token = Integer.toHexString((int) (Math.random() * 1000000));
        return String.format("%s?token=%s&licence=%s", urlDescarga, token, licencia);
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("-------------------------------------------------------");
        System.out.println(" 💾 FICHA TÉCNICA DE PRODUCTO DIGITAL (ITEM)           ");
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
        System.out.println("Entrega:       Inmediata vía enlace seguro con token");
    }

    @Override
    public boolean requiereEnvioFisico() {
        return false;
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return String.format("Digital [Formato: %s | Tamaño: %.1f MB | Licencia: %s]",
                formatoArchivo, tamanoMB, licencia);
    }

    // --- Getters y Setters Encapsulados ---

    @Override
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
        if (tamanoMB <= 0) {
            throw new IllegalArgumentException("[VALIDACIÓN ENCAPSULADA] El tamaño del archivo digital debe ser mayor a 0 MB.");
        }
        this.tamanoMB = tamanoMB;
    }

    public String getUrlDescarga() {
        return urlDescarga;
    }

    public void setUrlDescarga(String urlDescarga) {
        this.urlDescarga = urlDescarga;
    }

    @Override
    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }
}
