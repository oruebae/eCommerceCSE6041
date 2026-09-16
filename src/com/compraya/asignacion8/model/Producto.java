package com.compraya.asignacion8.model;

import com.compraya.asignacion8.exception.StockInvalidoException;

/**
 * Clase abstracta base Producto para la Asignación 8.
 * Incorpora encapsulamiento estricto y validaciones que lanzan excepciones personalizadas.
 */
public abstract class Producto {
    private final int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, String descripcion, double precio, int stock) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del producto debe ser un entero positivo.");
        }
        setNombre(nombre);
        this.descripcion = descripcion != null ? descripcion : "";
        setPrecio(precio);
        setStock(stock);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo ni estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion != null ? descripcion : "";
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException(String.format("El precio no puede ser negativo: $%.2f", precio));
        }
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new StockInvalidoException(stock);
        }
        this.stock = stock;
    }

    public abstract String getTipoProducto();

    @Override
    public String toString() {
        return String.format("%s [ID: %d | %s | $%.2f | Stock: %d]", 
                getTipoProducto(), id, nombre, precio, stock);
    }
}
