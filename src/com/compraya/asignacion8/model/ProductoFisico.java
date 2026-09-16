package com.compraya.asignacion8.model;

/**
 * Clase derivada ProductoFisico para la Asignación 8.
 */
public class ProductoFisico extends Producto {
    private double pesoKg;
    private String dimensiones;

    public ProductoFisico(int id, String nombre, String descripcion, double precio, int stock, double pesoKg, String dimensiones) {
        super(id, nombre, descripcion, precio, stock);
        setPesoKg(pesoKg);
        setDimensiones(dimensiones);
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        if (pesoKg < 0) {
            throw new IllegalArgumentException("El peso del producto físico no puede ser negativo.");
        }
        this.pesoKg = pesoKg;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones != null ? dimensiones : "N/A";
    }

    @Override
    public String getTipoProducto() {
        return "ProductoFísico";
    }
}
