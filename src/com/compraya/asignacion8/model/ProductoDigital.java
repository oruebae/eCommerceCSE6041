package com.compraya.asignacion8.model;

/**
 * Clase derivada ProductoDigital para la Asignación 8.
 */
public class ProductoDigital extends Producto {
    private String formato;
    private double tamanoMB;

    public ProductoDigital(int id, String nombre, String descripcion, double precio, int stock, String formato, double tamanoMB) {
        super(id, nombre, descripcion, precio, stock);
        setFormato(formato);
        setTamanoMB(tamanoMB);
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        if (formato == null || formato.trim().isEmpty()) {
            throw new IllegalArgumentException("El formato del producto digital no puede estar vacío.");
        }
        this.formato = formato.trim();
    }

    public double getTamanoMB() {
        return tamanoMB;
    }

    public void setTamanoMB(double tamanoMB) {
        if (tamanoMB < 0) {
            throw new IllegalArgumentException("El tamaño en MB del producto digital no puede ser negativo.");
        }
        this.tamanoMB = tamanoMB;
    }

    @Override
    public String getTipoProducto() {
        return "ProductoDigital";
    }
}
