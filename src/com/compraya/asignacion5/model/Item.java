package com.compraya.asignacion5.model;

import java.util.Objects;

/**
 * Clase Abstracta Base Item (Abstracción Avanzada - Asignación 5).
 * Abstrae las propiedades y comportamientos comunes de cualquier bien o servicio vendible en CompraYa.
 * Aplica Encapsulamiento Estricto con atributos privados y métodos abstractos obligatorios.
 */
public abstract class Item {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private Categoria categoria;

    public Item() {
    }

    public Item(int id, String nombre, String descripcion, double precio, int stock, Categoria categoria) {
        this.id = id;
        setNombre(nombre);
        this.descripcion = descripcion;
        setPrecio(precio);
        setStock(stock);
        this.categoria = categoria;
    }

    // --- Métodos Abstractos Obligatorios (Abstracción) ---

    /**
     * Muestra en consola la ficha técnica detallada del elemento.
     * Debe ser implementado por cada subclase concreta.
     */
    public abstract void mostrarDetalle();

    /**
     * Indica si el elemento requiere despacho o logística de envío físico.
     */
    public abstract boolean requiereEnvioFisico();

    /**
     * Retorna un resumen en texto de las métricas específicas de la subclase.
     */
    public abstract String obtenerDetallesEspecificos();

    // --- Métodos de Negocio Generales ---

    public boolean actualizarStock(int cantidad) {
        if (this.stock + cantidad < 0) {
            return false;
        }
        this.stock += cantidad;
        return true;
    }

    public void aplicarDescuento(double porcentaje) {
        if (porcentaje > 0 && porcentaje <= 100) {
            double descuento = this.precio * (porcentaje / 100.0);
            setPrecio(this.precio - descuento);
        }
    }

    // --- Getters y Setters Encapsulados con Validaciones ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("[VALIDACIÓN ENCAPSULADA] El nombre del producto no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException(String.format("[VALIDACIÓN ENCAPSULADA] El precio ($%.2f) no puede ser negativo.", precio));
        }
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException(String.format("[VALIDACIÓN ENCAPSULADA] El stock (%d) no puede ser negativo.", stock));
        }
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Item[ID: %d | Nombre: %s | Precio: $%.2f | Stock: %d]", id, nombre, precio, stock);
    }
}
